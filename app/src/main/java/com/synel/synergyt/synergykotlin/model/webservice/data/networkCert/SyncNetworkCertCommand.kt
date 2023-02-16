package com.synel.synergyt.synergykotlin.model.webservice.data.networkCert

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseCommand

data class SyncNetworkCertCommand(
    override val details: Details,
    override val id: Int
):BaseCommand()