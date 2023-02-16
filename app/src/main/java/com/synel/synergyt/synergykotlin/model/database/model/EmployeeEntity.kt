package com.synel.synergyt.synergykotlin.model.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Employee")
data class EmployeeEntity(
    val badgeNumber: String = "",
    val badgeAltNumber: String = "",
    val command: String = "",
    val devicePassword: String = "",
    val deviceRoleId: Int = 0,
    val employeeName: String = "",
    @PrimaryKey val employeeNo: String = "",
    val enableMealAttestation: Boolean = false,
    val enableMealLockout: Boolean = false,
    val enforceSchedule: Boolean = false,
    val faceAttestationAccepted: Boolean = false,
    val faceAttestationDate: String = "",
    val faceAttestationDeviceSn: String = "",
    val fpAttestationAccepted: Boolean = false,
    val fpAttestationDate: String = "",
    val fpAttestationDeviceSn: String = "",
    val managerEmployeeNum: String = "",
    val mealBreakLockoutPeriod: String = "",
    val verifyMode: String = ""
)
