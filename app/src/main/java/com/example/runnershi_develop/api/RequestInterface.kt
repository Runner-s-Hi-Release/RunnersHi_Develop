package com.example.runnershi_develop.api

import com.example.runnershi_develop.data.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RequestInterface {

    @GET("user/myprofile")
    suspend fun requestmyProfile(@Header("token") token : String): Response<ResponseData<User>>

    @GET("record/badge/detail/{flag}")
    suspend fun requestBadgeDetail(@Header("token") token : String, @Path("flag") flag : Int)
            : Response<ResponseData<BadgeDetail>>

}