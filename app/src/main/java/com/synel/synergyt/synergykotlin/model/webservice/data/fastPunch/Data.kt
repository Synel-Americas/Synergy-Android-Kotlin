package com.synel.synergyt.synergykotlin.model.webservice.data.fastPunch

data class Data(
    val addedBy: String,
    val attendanceKey: String,
    val customV1: String,
    val customV2: String,
    val empMaskStatus: Int,
    val empTemperature: String,
    val employeeNumber: String,
    val eventCode: String,
    val fpIndex: String,
    val invalidReason: String,
    val issueType: Int,
    val nonCheckFlag: Boolean,
    val punchDate: Long,
    val punchMode: String,
    val punchType: String,
    val status: String,
    val transactionId: Int,
    val valid: Boolean,
    val verifyType: String
)