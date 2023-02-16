package com.synel.synergyt.synergykotlin.model.webservice.data.attlogs

enum class EventCode(val value: Int) {
    NONE(-1),
    CLOCK_IN(0),
    CLOCK_OUT(1),
    MEAL_START(2),
    MEAL_END(3),
    BREAK_START(4),
    BREAK_END(5),
    TRANSFER(10),
    WAIVE_MEAL(20),
    TIP_ENTRY(30)
}