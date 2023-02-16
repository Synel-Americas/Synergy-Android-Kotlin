package com.synel.synergyt.synergykotlin.model.webservice.data.heartbeat

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseDetails

data class HeartBeatDetails(
    override val command: String
):BaseDetails()