package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class VisitorAttestationModuleResponse(
    val enableEmployeeVisitorLog: Boolean = false,
    val enableVisitorAttestationModule: Boolean = false,
    val enableVisitorCheckoutModule: Boolean = false,
    val enableVisitorPhotoCapture: Boolean = false,
    val visitorAttestationProfile: Int = 0
)