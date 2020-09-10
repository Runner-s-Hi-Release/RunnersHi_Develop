package com.example.runnershi_develop.api

import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import java.lang.Exception

sealed class ResultWrapper<out T>{
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null): ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
}

data class ErrorResponse(
    val errorDescription: String,
    val causes: Map<String, String> = emptyMap()
)

suspend fun <T>safeApiCall(call: suspend () -> T){
    withContext(Dispatchers.IO){
        try{
            ResultWrapper.Success(call.invoke())
        }catch (throwable: Throwable){
            when(throwable){
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.GenericError(code, errorResponse)
                }
                else ->{
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse?{
    return try{
        throwable.response()?.errorBody()?.source()?.let{
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    }catch (exception: Exception){
        null
    }
}