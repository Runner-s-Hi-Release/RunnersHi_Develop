package com.example.runnershi_develop.data


data class ResponseData<T>(
    val status : Int,
    val success : Boolean,
    val message : String,
    val result : T
)
