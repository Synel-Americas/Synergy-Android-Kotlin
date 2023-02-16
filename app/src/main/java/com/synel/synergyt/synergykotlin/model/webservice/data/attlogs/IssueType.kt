package com.synel.synergyt.synergykotlin.model.webservice.data.attlogs

enum class IssueType(val value:Int) {
    NO_ISSUE(0),
    OFFLINE_FAST_PUNCH(2),
    MEAL_LOCKOUT(4),
    EARLY_PUNCH_MEAL_LOCKOUT(6),
    OFF_SHIFT_PUNCH(8),
    INVALID_PUNCH_TIME(20),
    VISITOR_PUNCH(24),
    ATTESTATION_REJECTED(26),
    INVALID_ATTESTATION_CONFIG(28),
    EMP_BODYTEMP_TOO_HIGH(30),
    EMP_BODYTEMP_TOO_LOW(32),
    NO_FACE_MASK(34),
    SCHEDULE_DATA_UNAVAILABLE(36)
}