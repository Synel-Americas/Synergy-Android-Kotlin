package com.synel.synergyt.synergykotlin.model.webservice.data.orgCode

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.synel.synergyt.synergykotlin.model.webservice.base.BaseResponseBody
import com.synel.synergyt.synergykotlin.model.webservice.data.WSCMD
import com.synel.synergyt.synergykotlin.model.webservice.data.syncemployees.SyncEmployeesResponse

data class SyncOrgCodeResponse(
    override val cmd: Boolean,
    override val commands: List<SyncOrgCodeCommand>
) : BaseResponseBody() {
    override val type = WSCMD.UPDATE_ORG_CODES

    companion object {
        fun fromJson(json: String): SyncOrgCodeResponse {
            return Gson().fromJson(json, SyncOrgCodeResponse::class.java)
        }
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}