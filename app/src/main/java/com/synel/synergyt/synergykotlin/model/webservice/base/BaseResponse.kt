package com.synel.synergyt.synergykotlin.model.webservice.base


sealed class BaseResponse<out T>(
    val isSuccessful: Boolean,
    val data: T?,
    val errorResponse: BaseError?
) {
    data class Success<out T>(val responseData: T?) : BaseResponse<T>(true, responseData, null)
    data class Failure<out T>(val errorData: BaseError?) : BaseResponse<T>(false, null, errorData)
}