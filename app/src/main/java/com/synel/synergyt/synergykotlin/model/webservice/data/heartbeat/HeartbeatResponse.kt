package com.synel.synergyt.synergykotlin.model.webservice.data.heartbeat


data class HeartbeatResponse(
    val cmd: Boolean,
    val commands: List<Command>
)