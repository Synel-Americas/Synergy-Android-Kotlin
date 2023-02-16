package com.synel.synergyt.synergykotlin.model.webservice.base

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.synel.synergyt.synergykotlin.model.webservice.data.WSCMD

abstract class BaseResponseBody() {
    abstract val cmd: Boolean
    abstract val commands: List<BaseCommand>
    abstract val type: WSCMD


}