package com.synel.synergyt.synergykotlin.model.webservice.data.devicecmd

data class Data(
    val command: String,
    val commandId: Int,
    val content: String,
    val proceedDate: String,
    val returnCode: String
)