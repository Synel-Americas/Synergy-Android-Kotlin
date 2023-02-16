package com.synel.synergyt.synergykotlin.model.database.repository

import com.synel.synergyt.synergykotlin.model.database.AppDatabase
import com.synel.synergyt.synergykotlin.model.database.dao.EmployeeDao
import com.synel.synergyt.synergykotlin.model.database.model.EmployeeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmployeeRepository @Inject constructor(val appDatabase: AppDatabase) : EmployeeDao {

    private var employeeDao: EmployeeDao = appDatabase.employeeDao()

    override fun insertEmployee(employee: EmployeeEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            employeeDao.insertEmployee(employee)
        }
    }

    override fun getAll(): List<EmployeeEntity> {
        return employeeDao.getAll()
    }

    override fun insertEmployeeList(employees: List<EmployeeEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            employeeDao.insertEmployeeList(employees)
        }
    }

    override fun updateEmployee(employee: EmployeeEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            employeeDao.updateEmployee(employee)
        }
    }

    override fun updateEmployeeList(employees: List<EmployeeEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            employeeDao.updateEmployeeList(employees)
        }
    }

    override fun delete(employee: EmployeeEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            employeeDao.delete(employee)
        }
    }

    override fun getEmployeeByBadgeNumber(badgeNumber: String): EmployeeEntity {
        return employeeDao.getEmployeeByBadgeNumber(badgeNumber)
    }
}