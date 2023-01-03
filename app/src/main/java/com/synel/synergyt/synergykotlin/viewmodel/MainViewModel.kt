package com.synel.synergyt.synergykotlin.viewmodel

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
    val text: LiveData<String> = _text

    fun updateText() {
        _text.value = "Text updated!"
    }

    private val _employees = MutableLiveData<Employee>()
    val employees: LiveData<Employee> = _employees

    fun getEmployee(badgeNumber: String) {
        viewModelScope.launch {
            val employees = database.employeeDao().getEmployeeByBadgeNumber(badgeNumber)
            _employees.postValue(employees)
        }
    }


    fun addEmployee(employee: Employee) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            database.employeeDao().insert(employee)
        }

    }
}