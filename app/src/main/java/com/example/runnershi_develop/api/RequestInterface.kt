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

    @GET("record/all")
    suspend fun requestRunning(@Header("token") token : String)
            : ResponseData<List<NetworkRunning>>

    @GET("/record/detail/{run_idx}")
    suspend fun requestRunningDetail(@Header("token") token :String, @Path("run_idx") run_idx :Int):ResponseData<NetworkRunningDetail>

    @GET("/record/run/{run_idx}")
    suspend fun requestMyRunningDetail(@Header("token") token :String, @Path("run_idx") run_idx :Int):ResponseData<MyRunningDetail>

    @GET("/record/opponent/{game_idx}")
    suspend fun requestOpponentRunningDetail(@Header("token") token :String, @Path("game_idx") game_idx :Int):ResponseData<OpponentRunningDetail>
}