package com.synel.synergyt.synergykotlin.model.webservice.data.payCode

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseDetails

data class Details(
    override val command: String,
    val timeOffCodes: List<TimeOffCode>
):BaseDetails()