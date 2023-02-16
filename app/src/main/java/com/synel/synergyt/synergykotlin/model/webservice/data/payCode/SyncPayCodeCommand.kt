package com.synel.synergyt.synergykotlin.model.webservice.data.payCode

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseCommand

data class SyncPayCodeCommand(
    override val details: Details,
    override val id: Int
):BaseCommand()