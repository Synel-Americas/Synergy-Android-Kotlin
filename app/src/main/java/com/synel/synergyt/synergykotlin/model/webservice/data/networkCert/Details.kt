package com.synel.synergyt.synergykotlin.model.webservice.data.networkCert

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseDetails

data class Details(
    val client_cert: String,
    val client_passkey: String,
    override val command: String,
    val enable_network_authentication: Boolean,
    val server_cert: String
):BaseDetails()