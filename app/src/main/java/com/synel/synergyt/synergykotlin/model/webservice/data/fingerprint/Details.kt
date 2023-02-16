package com.synel.synergyt.synergykotlin.model.webservice.data.fingerprint

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseDetails

data class Details(
    override val command: String,
    val fpDataList: List<FpData>
):BaseDetails()