package com.synel.synergyt.synergykotlin.model.webservice.data.updateEmployee

data class Data(
    val badgeAltNumber: String,
    val badgeNumber: String,
    val clock_action: String,
    val devicePassword: String,
    val deviceRoleId: String,
    val deviceVerifyMode: String,
    val employeeNumber: String,
    val enableMealAttestation: String,
    val enableMealLockout: String,
    val enforceSchedule: Boolean,
    val faceAttestationAccepted: Boolean,
    val faceAttestationDate: String,
    val faceAttestationDeviceSn: String,
    val firstName: String,
    val formattedName: String,
    val fpAttestationAccepted: Boolean,
    val fpAttestationDate: String,
    val fpAttestationDeviceSn: String,
    val managerEmployeeNum: String,
    val mealBreakLockoutPeriod: String,
    val submitted_date: String,
    val sync_sent_time: String,
    val sync_status: Int,
    val transactionId: Int,
    val updated_date: String
)