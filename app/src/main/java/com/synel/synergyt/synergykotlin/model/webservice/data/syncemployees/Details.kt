package com.synel.synergyt.synergykotlin.model.webservice.data.syncemployees

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseDetails


data class Details(
    val badgeAltNumber: String,
    val badgeNumber: String,
    override val command: String,
    val devicePassword: String,
    val deviceRoleId: Int,
    val employeeName: String,
    val employeeNo: String,
    val enableMealAttestation: String,
    val enableMealLockout: String,
    val enforceSchedule: String,
    val faceAttestationAccepted: String,
    val faceAttestationDate: String,
    val faceAttestationDeviceSn: String,
    val fpAttestationAccepted: Boolean,
    val fpAttestationDate: String,
    val fpAttestationDeviceSn: String,
    val managerEmployeeNum: String,
    val mealBreakLockoutPeriod: String,
    val verifyMode: String
): BaseDetails()