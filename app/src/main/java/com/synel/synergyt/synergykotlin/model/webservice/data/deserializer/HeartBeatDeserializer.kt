package com.synel.synergyt.synergykotlin.model.webservice.data.deserializer

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.synel.synergyt.synergykotlin.model.webservice.base.BaseResponseBody
import com.synel.synergyt.synergykotlin.model.webservice.data.WSCMD
import com.synel.synergyt.synergykotlin.model.webservice.data.fingerprint.SyncFingerPrintsCommand
import com.synel.synergyt.synergykotlin.model.webservice.data.fingerprint.SyncFingerPrintsResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.heartbeat.HeartBeatCommand
import com.synel.synergyt.synergykotlin.model.webservice.data.heartbeat.HeartBeatDetails
import com.synel.synergyt.synergykotlin.model.webservice.data.heartbeat.HeartbeatResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.networkCert.SyncNetworkCertCommand
import com.synel.synergyt.synergykotlin.model.webservice.data.networkCert.SyncNetworkCertResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.orgCode.SyncOrgCodeCommand
import com.synel.synergyt.synergykotlin.model.webservice.data.orgCode.SyncOrgCodeResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.payCode.SyncPayCodeCommand
import com.synel.synergyt.synergykotlin.model.webservice.data.payCode.SyncPayCodeResponse
import com.synel.synergyt.synergykotlin.model.webservice.data.syncemployees.SyncEmployeesCommand
import com.synel.synergyt.synergykotlin.model.webservice.data.syncemployees.SyncEmployeesResponse
import timber.log.Timber
import java.lang.reflect.Type

class HeartBeatDeserializer : JsonDeserializer<MutableList<BaseResponseBody>> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): MutableList<BaseResponseBody> {

//        Timber.d("In HB deserializer")
        val gson = Gson()

        val response = gson.fromJson(json, HeartbeatResponse::class.java)
        val returnResponses = mutableListOf<BaseResponseBody>()

        val rootJson = json?.asJsonObject
        val cmdArr = rootJson?.get("commands")?.asJsonArray
        response.commands.forEachIndexed { index, responseCmd ->
            val command = try {
                WSCMD.strToCommandEnum(responseCmd.details.command)
            } catch (e: Exception) {
                WSCMD.UNKNOWN
            }
            val curJsonElement = cmdArr?.get(index)

//            Timber.d("Command type is [ %s ]", command)
//            Timber.d("jsonArr index($index) = [ %s ]", cmdArr?.get(index))
            when (command) {

                WSCMD.UPDATE_EMP -> {
                    val syncEmpCmd = gson.fromJson(curJsonElement, SyncEmployeesCommand::class.java)
                    val syncEmpResponse =
                        SyncEmployeesResponse(response.cmd, mutableListOf(syncEmpCmd))

                    returnResponses.add(syncEmpResponse)
                }

                WSCMD.UPDATE_ORG_CODES -> {
                    val syncOrgCodeCmd =
                        gson.fromJson(curJsonElement, SyncOrgCodeCommand::class.java)
                    val syncOrgCodeResponse =
                        SyncOrgCodeResponse(response.cmd, mutableListOf(syncOrgCodeCmd))

                    returnResponses.add(syncOrgCodeResponse)
                }

                WSCMD.UPDATE_PAY_CODE -> {
                    val syncPayCodeCmd =
                        gson.fromJson(curJsonElement, SyncPayCodeCommand::class.java)
                    val syncPayCodeResponse =
                        SyncPayCodeResponse(response.cmd, mutableListOf(syncPayCodeCmd))

                    returnResponses.add(syncPayCodeResponse)
                }

                WSCMD.UNKNOWN -> {
                    var commandStr = ""
                    var cmdId = -1
                    try {
                        commandStr = response.commands[0].details.command
                        cmdId = response.commands[0].id
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                    val heartBeatCommand = HeartBeatCommand(HeartBeatDetails(commandStr), cmdId)
                    val heartBeatResponse =
                        HeartbeatResponse(response.cmd, mutableListOf(heartBeatCommand))

                    returnResponses.add(heartBeatResponse)
                }

                WSCMD.UPDATE_FINGERPRINT -> {
                    val syncFingerPrintsCmd =
                        gson.fromJson(curJsonElement, SyncFingerPrintsCommand::class.java)
                    val syncFingerprintResponse =
                        SyncFingerPrintsResponse(response.cmd, mutableListOf(syncFingerPrintsCmd))

                    returnResponses.add(syncFingerprintResponse)
                }

                WSCMD.UPDATE_NETWORK_CERT -> {
                    val syncNetworkCertCmd =
                        gson.fromJson(curJsonElement, SyncNetworkCertCommand::class.java)
                    val syncNetworkCertResponse =
                        SyncNetworkCertResponse(response.cmd, mutableListOf(syncNetworkCertCmd))

                    returnResponses.add(syncNetworkCertResponse)
                }

                WSCMD.INFO -> Timber.e("INFO DESERIALIZER NOT IMPLEMENTED YET")//TODO INFO DESERIALIZER NOT IMPLEMENTED YET

                WSCMD.BROADCAST_MSG -> Timber.e("BROADCAST_MSG DESERIALIZER NOT IMPLEMENTED YET")//TODO BROADCAST_MSG DESERIALIZER NOT IMPLEMENTED YET

                WSCMD.LOAD_ATT_BY_DATE -> Timber.e("LOAD_ATT_BY_DATE DESERIALIZER NOT IMPLEMENTED YET")//TODO LOAD_ATT_BY_DATE DESERIALIZER NOT IMPLEMENTED YET

                WSCMD.LOAD_ENROLLED_EMP -> Timber.e("LOAD_ENROLLED_EMP DESERIALIZER NOT IMPLEMENTED YET")//TODO LOAD_ENROLLED_EMP DESERIALIZER NOT IMPLEMENTED YET

                WSCMD.UPGRADE -> Timber.e("UPGRADE DESERIALIZER NOT IMPLEMENTED YET")//TODO UPGRADE DESERIALIZER NOT IMPLEMENTED YET

                WSCMD.CLEAN_DB -> Timber.e("CLEAN_DB DESERIALIZER NOT IMPLEMENTED YET")//TODO CLEAN_DB DESERIALIZER NOT IMPLEMENTED YET

                else -> Timber.d("")
            }
        }

        Timber.d("Successfully serialized [ %s ] objects from [ %s ] responses ", returnResponses.size, cmdArr?.size()?:"ERR")
        return returnResponses
    }

}