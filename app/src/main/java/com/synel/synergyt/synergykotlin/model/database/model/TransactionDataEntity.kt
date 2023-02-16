package com.synel.synergyt.synergykotlin.model.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.synel.synergyt.synergykotlin.model.webservice.data.attlogs.*

@Entity(tableName = "TransactionData")
data class TransactionDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var attendanceKey: String = "",
    val customV1: String = "",
    val customV2: String = "",
    val customV3: String = "",
    val customV4: String = "",
    val customV5: String = "",
    val customV6: String = "",
    val customV7: String = "",
    val customV8: String = "",
    val customV9: String = "",
    val customV10: String = "",
    val customV11: String = "",
    val empMaskStatus: Int = -1,
    val empTemperature: String = "",
    val employeeNumber: String = "",
    val eventCode: EventCode = EventCode.NONE,
    val fpIndex: String = "",
    val issueType: IssueType = IssueType.NO_ISSUE,
    val nonCheckFlag: Boolean,
    val punchDate: String = "",
    val punchMode: PunchMode = PunchMode.NONE,
    val punch_photo_template: String = "",
    val status: PunchStatus = PunchStatus.PENDING,
    val verifyType: VerifyMode = VerifyMode.NONE
) {
    override fun toString(): String {
        return "Data(customV1='$customV1', customV2='$customV2', customV3='$customV3', customV4='$customV4', customV5='$customV5', customV6='$customV6', customV7='$customV7', customV8='$customV8', customV9='$customV9', customV10='$customV10', customV11='$customV11', empMaskStatus=$empMaskStatus, empTemperature='$empTemperature', employeeNumber='$employeeNumber', eventCode='$eventCode', fpIndex='$fpIndex', issueType=$issueType, nonCheckFlag=$nonCheckFlag, punchDate='$punchDate', punchMode=$punchMode, punch_photo_template='$punch_photo_template', status='$status', transactionId=$id, verifyType='$verifyType')"
    }
}
