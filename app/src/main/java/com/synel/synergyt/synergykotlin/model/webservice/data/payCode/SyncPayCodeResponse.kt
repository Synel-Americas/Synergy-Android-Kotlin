package com.synel.synergyt.synergykotlin.model.webservice.data.payCode

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.synel.synergyt.synergykotlin.model.webservice.base.BaseResponseBody
import com.synel.synergyt.synergykotlin.model.webservice.data.WSCMD
import com.synel.synergyt.synergykotlin.model.webservice.data.syncemployees.SyncEmployeesResponse

data class SyncPayCodeResponse(
    override val cmd: Boolean,
    override val commands: List<SyncPayCodeCommand>
) : BaseResponseBody() {
    override val type = WSCMD.UPDATE_PAY_CODE

    companion object {
        fun fromJson(json: String): SyncPayCodeResponse {
            return Gson().fromJson(json, SyncPayCodeResponse::class.java)
        }
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}