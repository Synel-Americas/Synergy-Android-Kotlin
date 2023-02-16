package com.synel.synergyt.synergykotlin.model.webservice.data.syncemployees

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseCommand

data class SyncEmployeesCommand(
    override val details: Details,
    override val id: Int
): BaseCommand()