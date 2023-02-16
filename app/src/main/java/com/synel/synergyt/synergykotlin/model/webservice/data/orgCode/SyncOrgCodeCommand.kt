package com.synel.synergyt.synergykotlin.model.webservice.data.orgCode

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseCommand

data class SyncOrgCodeCommand(
    override val details: Details,
    override val id: Int
):BaseCommand()