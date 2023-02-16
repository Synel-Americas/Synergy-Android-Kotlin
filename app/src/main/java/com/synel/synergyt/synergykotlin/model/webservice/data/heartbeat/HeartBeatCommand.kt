package com.synel.synergyt.synergykotlin.model.webservice.data.heartbeat

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseCommand

data class HeartBeatCommand(
    override val details: HeartBeatDetails,
    override val id: Int
): BaseCommand()