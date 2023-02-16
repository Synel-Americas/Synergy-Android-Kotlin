package com.synel.synergyt.synergykotlin.model.webservice.data

enum class WSCMD (val cmdStr: String) {
    INFO("INFO"),
    BROADCAST_MSG("BROADCAST PUBLIC MESSAGE"),
    LOAD_ATT_BY_DATE("LOAD ATT BY DATE"),
    LOAD_ENROLLED_EMP("LOAD ENROLLED EMPLOYEE"),
    UPGRADE("UPGRADE"),
    CLEAN_DB("CLEAN DB TABLE"),
    UPDATE_ORG_CODES("DATA UPDATE ORG CODES"),
    UPDATE_EMP("DATA UPDATE EMPLOYEE"),
    UPDATE_PAY_CODE("DATA UPDATE TIMEOFF PAY CODE"),
    UPDATE_FINGERPRINT("DATA UPDATE FINGERPRINT"),
    UPDATE_NETWORK_CERT("DATA UPDATE NETWORK CERTIFICATE"),
    UNKNOWN("UNKNOWN");

    companion object{
        fun strToCommandEnum(inputStr: String): WSCMD?{
            return values().firstOrNull {it.cmdStr.equals(inputStr, true)}
        }
    }

}

