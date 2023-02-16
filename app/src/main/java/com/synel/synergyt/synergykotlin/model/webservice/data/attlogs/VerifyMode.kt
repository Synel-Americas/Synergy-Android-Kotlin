package com.synel.synergyt.synergykotlin.model.webservice.data.attlogs

enum class VerifyMode(val value: Int) {
    NONE(0),
    PIN(1),
    PIN_AND_PASSWD(3),
    BADGE(4),
    BADGE_AND_PASSWD(6),
    FINGER(8),
    PIN_AND_FINGER(9),
    BADGE_AND_FINGER(12),
    FACE(16),
    PIN_AND_FACE(17),
    BADGE_AND_FACE(20)
}