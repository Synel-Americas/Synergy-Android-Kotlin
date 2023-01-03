package com.synel.synergyt.synergykotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synel.synergyt.synergykotlin.model.database.AppDatabase
import com.synel.synergyt.synergykotlin.model.database.Employee
import com.synel.synergyt.synergykotlin.model.webservice.WebServiceAPI
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val database: AppDatabase,
    private val apiService: WebServiceAPI
) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Hello, World!"
    }
    val userName = MutableLiveData<String>()
    val text: LiveData<String> = _text

    fun updateText() {
        _text.value = "Text updated!"
    }

    private val _employees = MutableLiveData<Employee>()
    val employees: LiveData<Employee> = _employees

    fun getEmployee(badgeNumber: String) {
        viewModelScope.launch {
            val employee = database.employeeDao().getEmployeeByBadgeNumber(badgeNumber)
            if (employee != null) {
                userName.postValue(employee.formattedName)
            } else {
                userName.postValue("")
            }
        }
    }


    fun addEmployee(employee: Employee) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            database.employeeDao().insert(employee)
        }

    }

    fun setUserName(badgeNumber:String) {
        Log.d("MainAcitivytViewModel", "setUserName manual postValue: $badgeNumber")
        userName.postValue(badgeNumber)
    }
}