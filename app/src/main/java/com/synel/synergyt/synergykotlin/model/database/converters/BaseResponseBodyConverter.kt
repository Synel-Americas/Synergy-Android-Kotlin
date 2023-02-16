package com.synel.synergyt.synergykotlin.model.database.converters

import androidx.room.TypeConverter
import com.synel.synergyt.synergykotlin.model.webservice.base.BaseResponseBody
import com.synel.synergyt.synergykotlin.model.webservice.data.fingerprint.SyncFingerPrintsResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.heartbeat.HeartbeatResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.networkCert.SyncNetworkCertResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.orgCode.SyncOrgCodeResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.payCode.SyncPayCodeResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.syncemployees.SyncEmployeesResponse


class BaseResponseBodyConverter {
    @TypeConverter
    fun fromBaseResponseBody(response: BaseResponseBody): String? {
        return when {
            (response is HeartbeatResponse) ->
                "HeartbeatResponse:" + response.toJson()

            (response is SyncEmployeesResponse) ->
                "SyncEmployeesResponse:" + response.toJson()

            (response is SyncPayCodeResponse) ->
                "SyncPayCodeResponse:" + response.toJson()

            (response is SyncOrgCodeResponse) ->
                "SyncOrgCodeResponse:" + response.toJson()

            (response is SyncFingerPrintsResponse) ->
                "SyncFingerPrintResponse:" + response.toJson()

            (response is SyncNetworkCertResponse) ->
                "SyncNetworkCertResponse:" + response.toJson()

            else -> null
        }
    }

    @TypeConverter
    fun toBaseResponseBody(data: String): BaseResponseBody? {
        return when {
            (data.startsWith("HeartbeatResponse:")) ->
                HeartbeatResponse.fromJson(data.substring("HeartbeatResponse:".length))

            (data.startsWith("SyncEmployeesResponse:")) ->
                SyncEmployeesResponse.fromJson(data.substring("SyncEmployeesResponse:".length))

            (data.startsWith("SyncPayCodeResponse:")) ->
                SyncPayCodeResponse.fromJson(data.substring("SyncPayCodeResponse:".length))

            (data.startsWith("SyncOrgCodeResponse:")) ->
                SyncOrgCodeResponse.fromJson(data.substring("SyncOrgCodeResponse:".length))

            (data.startsWith("SyncFingerPrintResponse:")) ->
                SyncFingerPrintsResponse.fromJson(data.substring("SyncFingerPrintResponse:".length))

            (data.startsWith("SyncNetworkCertResponse:")) ->
                SyncNetworkCertResponse.fromJson(data.substring("SyncNetworkCertResponse:".length))

            else -> null
        }
    }
}
