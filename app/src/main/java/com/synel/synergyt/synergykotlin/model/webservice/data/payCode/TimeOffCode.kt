package com.synel.synergyt.synergykotlin.model.webservice.data.payCode

data class TimeOffCode(
    val code: String,
    val description: String,
    val includeWeekend: Boolean,
    val unit: String
)