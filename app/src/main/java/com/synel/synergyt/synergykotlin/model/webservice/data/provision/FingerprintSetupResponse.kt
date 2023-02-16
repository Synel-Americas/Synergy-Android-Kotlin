package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class FingerprintSetupResponse(
    val displayFingerprintImage: String = "",
    val enableFingerprintDetection: String = "",
    val enrollmentThreshold: String = "",
    val matchThreshold: String = "",
    val trialTimesForMatching: String = ""
)