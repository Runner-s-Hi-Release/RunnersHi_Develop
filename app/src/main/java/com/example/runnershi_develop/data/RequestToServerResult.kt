package com.example.runnershi_develop.data

import java.lang.Exception

sealed class RequestToServerResult<out R>{
    data class Success<out T>(val data:T): RequestToServerResult<T>()
    data class Fail(val exception: Exception): RequestToServerResult<Nothing>()
}