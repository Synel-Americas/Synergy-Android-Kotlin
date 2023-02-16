package com.synel.synergyt.synergykotlin.model.webservice

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseResponse
import com.synel.synergyt.synergykotlin.model.webservice.base.BaseResponseBody
import com.synel.synergyt.synergykotlin.model.webservice.base.BaseWebServiceRepository
import com.synel.synergyt.synergykotlin.model.webservice.data.WSCMD
import com.synel.synergyt.synergykotlin.model.webservice.data.attlogs.AttLogsRequest
import com.synel.synergyt.synergykotlin.model.webservice.data.attlogs.AttLogsResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.devicecmd.DeviceCmdRequest
import com.synel.synergyt.synergykotlin.model.webservice.data.fastPunch.FastPunchResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.provision.ProvisionResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.updateEmployee.UpdateEmployeesRequest
import timber.log.Timber
import javax.inject.Inject

class WebServiceRepository @Inject constructor(private val webService: WebServiceAPI) :
    BaseWebServiceRepository(webService) {
    override suspend fun getProvision(stamp: String): BaseResponse<ProvisionResponse> {
        return handleRequest { webService.provision(stamp) }
    }

    override suspend fun getHeartbeat(stamp: String): BaseResponse<MutableList<BaseResponseBody>> {
        val hbResponse = handleRequest { webService.heartbeat(stamp) }

//        if (hbResponse.isSuccessful && hbResponse.data != null && hbResponse.data.cmd) {
//            if (hbResponse.data.commands.isNotEmpty())
//                hbResponse.data.commands.forEach { handleCommands(it.details.command) }
//        }
//        hbResponse.data?.forEach{
//            Timber.d("Type is: [ %s ]",it.type)
//            if(it.type== WSCMD.UPDATE_EMP){
//                Timber.d("SyncEmpResponse = [ %s ]", it as SyncEmployeesResponse)
//            }
//
//        }

        return hbResponse
    }

    override suspend fun sendDeviceCmd(request: DeviceCmdRequest): BaseResponse<Void> {
        return handleRequest { webService.devicecmd(request) }
    }

    override suspend fun sendPunch(request: AttLogsRequest): BaseResponse<AttLogsResponse> {
        return handleRequest { webService.sendPunch(request) }
    }

    override suspend fun getEmployees(stamp: String): BaseResponse<UpdateEmployeesRequest> {

        return handleRequest { webService.employees(stamp) }
    }

    override suspend fun sendFastPunch(
        stamp: String,
        ruleType: String,
        longestHours: Int
    ): BaseResponse<FastPunchResponse> {
        return handleRequest { webService.fastpunch(stamp, ruleType, longestHours) }
    }

    private fun handleCommands(cmdStr: String) {
        when (convertCmdToEnum(cmdStr)) {
            WSCMD.INFO -> Timber.d("Do handle INFO cmd")
            WSCMD.BROADCAST_MSG -> Timber.d("Do handle BROADCAST_MSG cmd")
            WSCMD.LOAD_ATT_BY_DATE -> Timber.d("Do handle LOAD_ATT_BY_DATE cmd")
            WSCMD.LOAD_ENROLLED_EMP -> Timber.d("Do handle LOAD_ENROLLED_EMP cmd")
            WSCMD.UPGRADE -> Timber.d("Do handle UPGRADE cmd")
            WSCMD.CLEAN_DB -> Timber.d("Do handle CLEAN_DB cmd")
            WSCMD.UPDATE_ORG_CODES -> Timber.d("Do handle DATA_UPDATE_ORG_CODES cmd")
            WSCMD.UPDATE_EMP -> Timber.d("Do handle DATA_UPDATE_EMP cmd")
            WSCMD.UPDATE_PAY_CODE -> Timber.d("Do handle DATA_UPDATE_TIMEOFF_CODE cmd")
            else -> Timber.e("Unknown command [ $cmdStr ]")
        }
    }

    private fun convertCmdToEnum(cmdStr: String): WSCMD {
        var cmd = WSCMD.UNKNOWN
        try {
            cmd = WSCMD.strToCommandEnum(cmdStr)?:WSCMD.UNKNOWN
        } catch (e: Exception) {
            Timber.e(e)
        }
        return cmd
    }

}