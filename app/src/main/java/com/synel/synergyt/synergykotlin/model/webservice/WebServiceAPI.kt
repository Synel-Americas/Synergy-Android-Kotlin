package com.synel.synergyt.synergykotlin.model.webservice

import com.synel.synergyt.synergykotlin.model.webservice.data.fastPunch.FastPunchResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.heartbeat.HeartbeatResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.provision.ProvisionResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.attlogs.AttLogsRequest
import com.synel.synergyt.synergykotlin.model.webservice.data.attlogs.AttLogsResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.devicecmd.DeviceCmdRequest
import com.synel.synergyt.synergykotlin.model.webservice.data.employees.EmployeesResponse
import retrofit2.Response
import retrofit2.http.*

interface WebServiceAPI {
////http://104.209.159.19:8006/ZKAndroid/api_v1/123456789
    companion object {
        const val BASE_URL = "http://104.209.159.19:8006/"
    }

    @GET("ZKAndroid/api_v1/{sn}/provision")
    suspend fun provision(
        @Path("sn") serialNum: String,
        @Query("stamp") stamp: String
    ): Response<ProvisionResponse>

    @GET("ZKAndroid/api_v1/{sn}/heartbeat")
    suspend fun heartbeat(
        @Path("sn") serialNum: String,
        @Query("stamp") stamp: String
    ): Response<HeartbeatResponse>

    @POST("ZKAndroid/api_v1/{sn}/devicecmd")
    suspend fun devicecmd(
        @Path("sn") serialNum: String,
        @Body request: DeviceCmdRequest
    ): Response<Void>

    @POST("attlogs")
    suspend fun attlogs(
        @Path("sn") serialNum: String,
        @Body request: AttLogsRequest
    ): Response<AttLogsResponse>

    @GET("employees")
    suspend fun employees(
        @Path("sn") serialNum: String,
        @Query("stamp") stamp: String
    ): Response<EmployeesResponse>

    @POST("ZKAndroid/api_v1/{sn}/attlogs/fastpunch")
    suspend fun fastpunch(
        @Path("sn") serialNum: String,
        @Query("stamp") stamp: String,
        @Query("ruleType") ruleType: String,
        @Query("longestHours") longestHours: Int
    ): Response<FastPunchResponse>
}