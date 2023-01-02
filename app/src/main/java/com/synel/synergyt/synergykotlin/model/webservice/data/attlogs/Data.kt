package com.synel.synergyt.synergykotlin.model.webservice.data.attlogs

data class Data(
    val attendanceKey: String,
    val customV1: String,
    val customV10: String,
    val customV11: String,
    val customV2: String,
    val customV3: String,
    val customV4: String,
    val customV5: String,
    val customV6: String,
    val customV7: String,
    val customV8: String,
    val customV9: String,
    val empMaskStatus: Int,
    val empTemperature: String,
    val employeeNumber: String,
    val eventCode: String,
    val fpIndex: String,
    val issueType: Int,
    val nonCheckFlag: Boolean,
    val punchDate: String,
    val punchMode: Int,
    val punch_photo_template: String,
    val status: String,
    val transactionId: Int,
    val verifyType: String
)