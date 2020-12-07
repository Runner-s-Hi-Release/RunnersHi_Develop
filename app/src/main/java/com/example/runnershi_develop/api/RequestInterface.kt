package com.example.runnershi_develop.api

import com.example.runnershi_develop.data.*
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RequestInterface {

    @GET("user/myprofile")
    suspend fun requestmyProfile(@Header("jwt") token : String): ResponseData<User>

    @GET("record/badge/detail/{flag}")
    suspend fun requestBadgeDetail(@Header("jwt") token : String, @Path("flag") flag : Int): ResponseData<BadgeDetail>

    @GET("record/all")
    suspend fun requestRunning(@Header("jwt") token : String)
            : ResponseData<List<NetworkRunning>>

    @GET("/record/detail/{run_idx}")
    suspend fun requestRunningDetail(@Header("jwt") token :String, @Path("run_idx") run_idx :Int):ResponseData<NetworkRunningDetail>

    @GET("/record/run/{run_idx}")
    suspend fun requestMyRunningDetail(@Header("jwt") token :String, @Path("run_idx") run_idx :Int):ResponseData<NetworkMyRunningDetail>

    @GET("ranking/runners")
    suspend fun requestHeavyRunner(@Header("jwt") token :String): ResponseData<List<RankingRunner.HeavyRunner>>

    @GET("ranking/winners")
    suspend fun requestWinningRunner(@Header("jwt") token :String):ResponseData<List<RankingRunner.WinOrLoseRunner>>

    @GET("ranking/losers")
    suspend fun requestLosingRunner(@Header("jwt") token :String): ResponseData<List<RankingRunner.WinOrLoseRunner>>

    suspend fun requestOpponentRunningDetail(
        @Header("jwt") token: String,
        @Path("game_idx") game_idx: Int
    ): ResponseData<NetworkOpponentRunningDetail>

    @GET("running/ranking/{run_idx}")
    suspend fun requestRaceRanking(@Header("jwt") token :String, @Path("run_idx") run_idx :Int) : ResponseData<RaceRanking>
}