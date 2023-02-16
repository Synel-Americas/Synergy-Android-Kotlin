package com.synel.synergyt.synergykotlin.model.webservice.data.attlogs

enum class PunchMode(val value:Int) {
    NONE(0),
    NORMAL_PUNCH(1),
    FAST_PUNCH_LOCAL_CALC(2),
    FAST_PUNCH_SERVER_CALC(3)
}