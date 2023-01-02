package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class VisitorAttestationModule(
    val enableEmployeeVisitorLog: Boolean,
    val enableVisitorAttestationModule: Boolean,
    val enableVisitorCheckoutModule: Boolean,
    val enableVisitorPhotoCapture: Boolean,
    val visitorAttestationProfile: Int
)