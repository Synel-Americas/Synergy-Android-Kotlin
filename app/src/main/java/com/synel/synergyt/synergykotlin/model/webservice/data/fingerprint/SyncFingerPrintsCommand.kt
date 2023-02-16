package com.synel.synergyt.synergykotlin.model.webservice.data.fingerprint

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseCommand

data class SyncFingerPrintsCommand(
    override val details: Details,
    override val id: Int
):BaseCommand()