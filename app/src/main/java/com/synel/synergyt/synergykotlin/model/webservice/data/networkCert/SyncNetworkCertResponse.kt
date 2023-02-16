package com.synel.synergyt.synergykotlin.model.webservice.data.networkCert

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.synel.synergyt.synergykotlin.model.webservice.base.BaseResponseBody
import com.synel.synergyt.synergykotlin.model.webservice.data.WSCMD
import com.synel.synergyt.synergykotlin.model.webservice.data.syncemployees.SyncEmployeesResponse

data class SyncNetworkCertResponse(
    override val cmd: Boolean,
    override val commands: List<SyncNetworkCertCommand>
) : BaseResponseBody() {
    override val type = WSCMD.UPDATE_NETWORK_CERT

    companion object {
        fun fromJson(json: String): SyncNetworkCertResponse {
            return Gson().fromJson(json, SyncNetworkCertResponse::class.java)
        }
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }
}