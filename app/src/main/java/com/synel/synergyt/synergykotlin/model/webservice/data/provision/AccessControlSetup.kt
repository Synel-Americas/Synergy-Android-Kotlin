package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class AccessControlSetup(
    val enableAccessControl: String,
    val enableRelay1: String,
    val enableRelay2: String,
    val relayTimeout: String
)