package com.synel.synergyt.synergykotlin.model.webservice.data.attlogs

data class Data(
    var attendanceKey: String,
    val customV1: String,
    val customV2: String,
    val customV3: String,
    val customV4: String,
    val customV5: String,
    val customV6: String,
    val customV7: String,
    val customV8: String,
    val customV9: String,
    val customV10: String,
    val customV11: String,
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
) {
    override fun toString(): String {
        return "Data(customV1='$customV1', customV2='$customV2', customV3='$customV3', customV4='$customV4', customV5='$customV5', customV6='$customV6', customV7='$customV7', customV8='$customV8', customV9='$customV9', customV10='$customV10', customV11='$customV11', empMaskStatus=$empMaskStatus, empTemperature='$empTemperature', employeeNumber='$employeeNumber', eventCode='$eventCode', fpIndex='$fpIndex', issueType=$issueType, nonCheckFlag=$nonCheckFlag, punchDate='$punchDate', punchMode=$punchMode, punch_photo_template='$punch_photo_template', status='$status', transactionId=$transactionId, verifyType='$verifyType')"
    }
}