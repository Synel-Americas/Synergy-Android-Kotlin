package com.synel.synergyt.synergykotlin.model.webservice.data.syncemployees

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.synel.synergyt.synergykotlin.model.webservice.base.BaseResponseBody
import com.synel.synergyt.synergykotlin.model.webservice.data.WSCMD

data class SyncEmployeesResponse(
    override val cmd: Boolean,
    override val commands: List<SyncEmployeesCommand>
): BaseResponseBody() {
    override val type = WSCMD.UPDATE_EMP

    companion object {
        fun fromJson(json: String): SyncEmployeesResponse {
            return Gson().fromJson(json, SyncEmployeesResponse::class.java)
        }
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }

}
