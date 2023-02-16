package com.synel.synergyt.synergykotlin.model.database.dao

import androidx.room.*
import com.synel.synergyt.synergykotlin.model.database.model.EmployeeEntity

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employee")
    fun getAll(): List<EmployeeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: EmployeeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployeeList(employees: List<EmployeeEntity>)

    @Update
    fun updateEmployee(employee: EmployeeEntity)

    @Update
    fun updateEmployeeList(employees: List<EmployeeEntity>)

    @Delete
    fun delete(employee: EmployeeEntity)

    @Query("SELECT * FROM employee where employee.badgeNumber = :badgeNumber")
    fun getEmployeeByBadgeNumber(badgeNumber: String): EmployeeEntity
}