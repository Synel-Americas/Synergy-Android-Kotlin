package com.synel.synergyt.synergykotlin.model.webservice.data.fingerprint

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.synel.synergyt.synergykotlin.model.webservice.base.BaseResponseBody
import com.synel.synergyt.synergykotlin.model.webservice.data.WSCMD
import com.synel.synergyt.synergykotlin.model.webservice.data.syncemployees.SyncEmployeesResponse

data class SyncFingerPrintsResponse(
    override val cmd: Boolean,
    override val commands: List<SyncFingerPrintsCommand>
) : BaseResponseBody() {
    override val type = WSCMD.UPDATE_FINGERPRINT

    companion object {
        fun fromJson(json: String): SyncFingerPrintsResponse {
            return Gson().fromJson(json, SyncFingerPrintsResponse::class.java)
        }
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}