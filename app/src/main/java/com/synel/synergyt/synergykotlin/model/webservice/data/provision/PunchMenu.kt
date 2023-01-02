package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class PunchMenu(
    val action: String,
    val addClosePunch: Boolean,
    val attest_profile: Int,
    val closePunchEventCode: String,
    val codeLevels: List<CodeLevel>,
    val enablePhotoCapture: Boolean,
    val eventCode: String,
    val keyNumber: Int,
    val label: String,
    val menuIcon: String,
    val rules: List<String>,
    val visitorAllowed: Boolean
)