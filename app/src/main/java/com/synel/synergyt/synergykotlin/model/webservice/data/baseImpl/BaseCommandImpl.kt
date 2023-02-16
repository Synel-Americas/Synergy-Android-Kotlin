package com.synel.synergyt.synergykotlin.model.webservice.data.baseImpl

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseCommand
import com.synel.synergyt.synergykotlin.model.webservice.base.BaseDetails

data class BaseCommandImpl(override val details: BaseDetails, override val id: Int):BaseCommand()
