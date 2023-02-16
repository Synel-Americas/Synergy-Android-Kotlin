package com.synel.synergyt.synergykotlin.model.webservice.data.baseImpl

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.synel.synergyt.synergykotlin.model.webservice.base.BaseCommand
import com.synel.synergyt.synergykotlin.model.webservice.base.BaseResponseBody
import com.synel.synergyt.synergykotlin.model.webservice.data.WSCMD

data class BaseResponseBodyImpl(override val cmd: Boolean, override val commands: List<BaseCommand>):BaseResponseBody() {
    override val type =WSCMD.UNKNOWN
    var jsonValue: JsonElement = JsonObject()
}
