package com.synel.synergyt.synergykotlin.model.database

import androidx.room.*
import com.synel.synergyt.synergykotlin.model.database.Employee

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employee")
    suspend fun getAll(): List<Employee>

    @Insert
    suspend fun insert(employee: Employee)

    @Update
    suspend fun update(employee: Employee)

    @Delete
    suspend fun delete(employee: Employee)

    @Query("SELECT * FROM employee where employee.badgeNumber = :badgeNumber")
    suspend fun getEmployeeByBadgeNumber(badgeNumber: String): Employee
}