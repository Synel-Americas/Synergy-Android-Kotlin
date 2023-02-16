package com.synel.synergyt.synergykotlin.model.webservice.base

import android.util.MalformedJsonException
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import java.net.ConnectException


/**
 * Base interface to be implemented by all repositories
 */
interface RepositoryInterface {

    suspend fun <T> handleRequest(call: suspend () -> Response<T>): BaseResponse<T> {
        return try {
            val apiResponse = call.invoke()
            if (call is Call<*>) {
                Timber.d("call request = [ %s ]", call.request())
            } else{
                Timber.d("call is [ %s ]", call.javaClass.simpleName);
            }
            Timber.d("apiResponse is [ %s ]", apiResponse.javaClass.simpleName);
            Timber.d("apiResponse.body = [ %s ]", apiResponse.body())
            Timber.d("apiResponse.message = [ %s ]", apiResponse.message())
            Timber.d("apiResponse.message = [ %s ]", apiResponse.headers())


            if (apiResponse.isSuccessful) {
                BaseResponse.Success(apiResponse.body())
            } else {
                handleFailureResponse(apiResponse.errorBody())
            }
        } catch (ex: Exception) {
            Timber.e(ex);
            handleException(ex)
        }
    }

    fun <T> handleFailureResponse(response: ResponseBody?): BaseResponse<T> {
        response?.let {
            Timber.d("it = [ %s ]", it.string());
            val jsonObject = JSONObject(it.string())
            try {
                val code = jsonObject.getInt("status")
                val error = jsonObject.getString("error")
                val errorMessage = jsonObject.getString("message")

                val errorCode: ErrorCode = when (code) {
                    401 -> ErrorCode.UNAUTHORIZED
                    404 -> ErrorCode.NOT_FOUND
                    else -> ErrorCode.UNKNOWN
                }

                return BaseResponse.Failure(BaseError(errorCode, error, errorMessage))
            } catch (ex: Exception) {

                Timber.e(ex);
            }
        }

        return BaseResponse.Failure(BaseError(ErrorCode.UNKNOWN))
    }

    private fun <T> handleException(exception: Exception?): BaseResponse<T> {
        exception?.let {
            val errorResponse = when (exception) {
                is ConnectException -> {
                    BaseError(ErrorCode.NO_NETWORK)
                }
                is MalformedJsonException -> {
                    BaseError(ErrorCode.BAD_RESPONSE)
                }
                else -> {
                    BaseError(ErrorCode.UNKNOWN)
                }
            }
            return BaseResponse.Failure(errorResponse)
        }
        return BaseResponse.Failure(BaseError(ErrorCode.UNKNOWN))
    }
}