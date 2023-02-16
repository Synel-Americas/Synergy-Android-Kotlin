package com.synel.synergyt.synergykotlin.model.webservice.base

import com.synel.synergyt.synergykotlin.model.webservice.WebServiceAPI
import com.synel.synergyt.synergykotlin.model.webservice.data.attlogs.AttLogsRequest
import com.synel.synergyt.synergykotlin.model.webservice.data.attlogs.AttLogsResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.devicecmd.DeviceCmdRequest
import com.synel.synergyt.synergykotlin.model.webservice.data.updateEmployee.UpdateEmployeesRequest
import com.synel.synergyt.synergykotlin.model.webservice.data.fastPunch.FastPunchResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.heartbeat.HeartbeatResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.provision.ProvisionResponse

abstract class BaseWebServiceRepository(webService: WebServiceAPI) : RepositoryInterface {

    abstract suspend fun getProvision(stamp: String): BaseResponse<ProvisionResponse>

    abstract suspend fun getHeartbeat(stamp: String): BaseResponse<MutableList<BaseResponseBody>>

    abstract suspend fun sendDeviceCmd(request: DeviceCmdRequest): BaseResponse<Void>

    abstract suspend fun sendPunch(request: AttLogsRequest): BaseResponse<AttLogsResponse>

    abstract suspend fun getEmployees(stamp: String): BaseResponse<UpdateEmployeesRequest>

    abstract suspend fun sendFastPunch(
        stamp: String,
        ruleType: String,
        longestHours: Int
    ): BaseResponse<FastPunchResponse>
}