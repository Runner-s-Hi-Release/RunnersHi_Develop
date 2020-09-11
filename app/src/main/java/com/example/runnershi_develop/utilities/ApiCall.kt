package com.example.runnershi_develop.utilities

import com.example.runnershi_develop.data.RequestToServerResult
import retrofit2.Response

suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): RequestToServerResult<T> {
    val response: Response<T>
    try {
        response = call.invoke()
    } catch (t: Throwable) {
        return RequestToServerResult.Fail(Exception(t))
    }
    if (!response.isSuccessful) {
        val responseErrorBody = response.errorBody()
        if (responseErrorBody != null) {
            return RequestToServerResult.Fail(Exception(response.errorBody().toString()))
        }
        return RequestToServerResult.Fail(Exception(response.raw().message))
    }
    return RequestToServerResult.Success(response.body()!!)
}