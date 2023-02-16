package com.synel.synergyt.synergykotlin.viewmodel

import android.content.res.AssetManager
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synel.synergyt.synergykotlin.R
import com.synel.synergyt.synergykotlin.model.database.AppDatabase
import com.synel.synergyt.synergykotlin.model.database.model.EmployeeEntity
import com.synel.synergyt.synergykotlin.model.database.model.TransactionDataEntity
import com.synel.synergyt.synergykotlin.model.database.model.WebserviceCommandEntity
import com.synel.synergyt.synergykotlin.model.datastore.PreferencesRepository
import com.synel.synergyt.synergykotlin.model.datastore.SynergyPreferencesRepository
import com.synel.synergyt.synergykotlin.model.webservice.WebServiceRepository
import com.synel.synergyt.synergykotlin.model.webservice.data.attlogs.*
import com.synel.synergyt.synergykotlin.model.webservice.data.provision.ProvisionResponse
import com.synel.synergyt.synergykotlin.utils.MappingUtility
import com.synel.synergyt.synergykotlin.utils.readResourcesFile
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import timber.log.Timber
import java.time.ZonedDateTime
import java.util.concurrent.Executors
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val database: AppDatabase,
    private val webRepository: WebServiceRepository,
    private val dataRepo: PreferencesRepository
) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Hello, World!"
    }
    val userName = MutableLiveData<String>()
    val text: LiveData<String> = _text

    fun updateText() {
        _text.value = "Text updated!"
    }

    private val _employees = MutableLiveData<EmployeeEntity>()
    val employees: LiveData<EmployeeEntity> = _employees

    fun getEmployee(badgeNumber: String) {
        viewModelScope.launch {
            val employee = database.employeeDao().getEmployeeByBadgeNumber(badgeNumber)
            if (employee != null) {
                userName.postValue(employee.employeeName)
            } else {
                userName.postValue("")
            }
        }
    }

    fun getHeartbeat() {
        viewModelScope.launch {
            val hbResponse = webRepository.getHeartbeat("2023-01-12T02:00:38.329-07:00")
            val commandList = mutableListOf<WebserviceCommandEntity>()
            val curTime = ZonedDateTime.now()
            hbResponse.data?.forEach {
                try {
                    val id = it.commands[0].id
                    val cmdType = it.type
                    val isProcessed = false
                    commandList.add(
                        WebserviceCommandEntity(
                            id,
                            isProcessed,
                            cmdType,
                            curTime,
                            it
                        )
                    )
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
            Executors.newSingleThreadExecutor().execute {
                database.webServiceCommandDao().saveCommands(commandList)
            }
        }
    }

    fun getProvision() {
        viewModelScope.launch {
            val response = webRepository.getProvision("2023-01-12T02:00:38.329-07:00")
            if (response.isSuccessful) {
                val provisionResponse = response.data as ProvisionResponse
                Timber.d("provisionResponse = [ %s ]", provisionResponse);

                val provision = MappingUtility.mapProvisionResponseToProvision(provisionResponse)

                dataRepo.updateProvision(provision)

                dataRepo.provision.collect() {
                    Timber.d("dataRepo.provision = [ %s ]", it);
                    Timber.d(
                        "thermModule.enableThermalModule = [ %s ]",
                        it.deviceSetup.thermModule.enableThermalModule
                    );

                }

            }
        }
    }

    fun sendPunch() {

//        viewModelScope.launch {
        Executors.newSingleThreadExecutor().execute {
            val transaction = TransactionDataEntity(
                0,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                0,
                "",
                "481",
                EventCode.CLOCK_IN,
                "",
                IssueType.NO_ISSUE,
                false,
                System.currentTimeMillis().toString(),
                PunchMode.NORMAL_PUNCH,
                "",
                PunchStatus.OPEN,
                VerifyMode.PIN
            ).also {
                it.attendanceKey = MappingUtility.createAttendanceKey(it)
            }
            database.transactionDao().saveTransaction(transaction)

            val dbTransaction = database.transactionDao().getLastTransactionByEmployeeNumber("481")
            Timber.d("transaction = [ %s ]", dbTransaction)
            val webTransaction =
                MappingUtility.mapTransactionDataToAttlogsRequest(dbTransaction).data[0]
            Timber.d("attlogsTransaction = [ %s ]", webTransaction)

            viewModelScope.launch {
                webRepository.sendPunch(
                    MappingUtility.mapTransactionDataToAttlogsRequest(
                        dbTransaction
                    )
                )
            }
        }
    }

    fun getEmployees(resources: Resources) {

        val jsonObj = resources.readResourcesFile(R.raw.empeon_employees)

//        Timber.d("jsonObj = [ %s ]", jsonObj);

        try {
            val jsonObj2 = JSONTokener(jsonObj).nextValue() as JSONObject

            jsonObj2.keys().forEachRemaining {
                Timber.d("jsonObj2 key = [ %s ]", it)
            }
            val jsonArr = jsonObj2.getJSONArray("commands")
            for(i in 0 until jsonArr.length()){
                Timber.d("jsonArr commands obj[$i] = [ %s ]", jsonArr.getJSONObject(i))
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }


    fun addEmployee(employee: EmployeeEntity) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            database.employeeDao().insertEmployee(employee)
        }
    }

    fun setUserName(badgeNumber: String) {
        Log.d("MainAcitivytViewModel", "setUserName manual postValue: $badgeNumber")
        userName.postValue(badgeNumber)
    }
}