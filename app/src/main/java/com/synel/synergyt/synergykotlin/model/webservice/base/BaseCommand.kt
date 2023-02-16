package com.synel.synergyt.synergykotlin.model.webservice.base


abstract class BaseCommand() {
    abstract val details: BaseDetails
    abstract val id: Int
}
