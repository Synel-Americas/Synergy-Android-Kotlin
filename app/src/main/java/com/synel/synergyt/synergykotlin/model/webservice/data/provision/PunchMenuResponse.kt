package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class PunchMenuResponse(
    val action: String = "",
    val addClosePunch: Boolean = false,
    val attest_profile: Int = 0,
    val closePunchEventCode: String = "",
    val codeLevels: List<CodeLevelResponse>? = mutableListOf(CodeLevelResponse()),
    val enablePhotoCapture: Boolean = false,
    val eventCode: String = "",
    val keyNumber: Int = 0,
    val label: String = "",
    val menuIcon: String = "",
    val rules: List<String> = mutableListOf(""),
    val visitorAllowed: Boolean = false
)