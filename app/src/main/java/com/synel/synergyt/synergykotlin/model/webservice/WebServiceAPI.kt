package com.synel.synergyt.synergykotlin.model.webservice

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseResponseBody
import com.synel.synergyt.synergykotlin.model.webservice.data.fastPunch.FastPunchResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.heartbeat.HeartbeatResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.provision.ProvisionResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.attlogs.AttLogsRequest
import com.synel.synergyt.synergykotlin.model.webservice.data.attlogs.AttLogsResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.devicecmd.DeviceCmdRequest
import com.synel.synergyt.synergykotlin.model.webservice.data.updateEmployee.UpdateEmployeesRequest
import retrofit2.Response
import retrofit2.http.*

interface WebServiceAPI {
////http://104.209.159.19:8006/ZKAndroid/api_v1/123456789
    companion object {
//        const val BASE_URL = "http://104.209.159.19:8006/"
const val BASE_URL = "http://104.209.159.19:8006/ZKAndroid/api_v1/123456789/"
    }

//    @GET("ZKAndroid/api_v1/{sn}/provision")
    @GET("provision")
    suspend fun provision(
        @Query("stamp") stamp: String
    ): Response<ProvisionResponse>

    @GET("heartbeat")
    suspend fun heartbeat(
        @Query("stamp") stamp: String
    ): Response<MutableList<BaseResponseBody>>

    @POST("devicecmd")
    suspend fun devicecmd(
        @Body request: DeviceCmdRequest
    ): Response<Void>

    @POST("attlogs")
    suspend fun sendPunch(
        @Body request: AttLogsRequest
    ): Response<AttLogsResponse>

    @GET("employees")
    suspend fun employees(
        @Query("stamp") stamp: String
    ): Response<UpdateEmployeesRequest>

    @POST("attlogs/fastpunch")
    suspend fun fastpunch(
        @Query("stamp") stamp: String,
        @Query("ruleType") ruleType: String,
        @Query("longestHours") longestHours: Int
    ): Response<FastPunchResponse>
}