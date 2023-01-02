package com.synel.synergyt.synergykotlin.model.webservice

import com.synel.synergyt.synergykotlin.model.webservice.data.fastPunch.FastPunchResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.heartbeat.HeartbeatResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.provision.ProvisionResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.attlogs.AttLogsRequest
import com.synel.synergyt.synergykotlin.model.webservice.data.attlogs.AttLogsResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.devicecmd.DeviceCmdRequest
import com.synel.synergyt.synergykotlin.model.webservice.data.employees.EmployeesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WebServiceAPI {

    companion object {
        const val BASE_URL = ""
    }

    @GET("provision")
    suspend fun provision(@Query("stamp") stamp: String): Response<ProvisionResponse>

    @GET("heartbeat")
    suspend fun heartbeat(@Query("stamp") stamp: String): Response<HeartbeatResponse>

    @POST("devicecmd")
    suspend fun devicecmd(@Body request: DeviceCmdRequest): Response<Void>

    @POST("attlogs")
    suspend fun attlogs(@Body request: AttLogsRequest): Response<AttLogsResponse>

    @GET("employees")
    suspend fun employees(@Query("stamp") stamp: String): Response<EmployeesResponse>

    @GET("fastpunch")
    suspend fun fastpunch(
        @Query("stamp") stamp: String,
        @Query("ruleType") ruleType: String,
        @Query("longestHours") longestHours: Int
    ): Response<FastPunchResponse>
}