package com.synel.synergyt.synergykotlin.model.webservice.base



data class BaseError(
    val code: ErrorCode = ErrorCode.UNKNOWN,
    val error: String = "",
    val message: String = ""
) {
    override fun toString(): String {
        return code.name + " - " + error + ": " + message
    }
}

enum class ErrorCode {
    UNAUTHORIZED,
    NOT_FOUND,
    NO_NETWORK,
    BAD_RESPONSE,
    UNKNOWN
}