package com.example.runnershi_develop.api

import com.example.runnershi_develop.data.*
import retrofit2.http.*

interface RequestInterface {

    @POST("user/signUUID")
    suspend fun requestToken(@Body uuid: UUID): ResponseData<User>

    @POST("running/find")
    suspend fun requestRunningFind(@Header("jwt") token : String, @Body runningStart: RunningStart): ResponseData<Any>

    @DELETE("running/stopMatching")
    suspend fun requestStopMatching(@Header("jwt") token : String): ResponseData<Any>

    @GET("running/confirm")
    suspend fun requestConfirm(@Header("jwt") token : String): ResponseData<Opponent>

    @GET("record/badge/detail/{flag}")
    suspend fun requestBadgeDetail(@Header("token") token : String, @Path("flag") flag : Int): ResponseData<BadgeDetail>

    @GET("record/all")
    suspend fun requestRunning(@Header("token") token : String)
            : ResponseData<List<NetworkRunning>>

    @GET("/record/detail/{run_idx}")
    suspend fun requestRunningDetail(@Header("token") token :String, @Path("run_idx") run_idx :Int):ResponseData<NetworkRunningDetail>

    @GET("/record/run/{run_idx}")
    suspend fun requestMyRunningDetail(@Header("token") token :String, @Path("run_idx") run_idx :Int):ResponseData<NetworkMyRunningDetail>

    @GET("ranking/runner")
    suspend fun requestHeavyRunner(@Header("jwt") token :String): ResponseData<List<RankingRunner.HeavyRunner>>

    @GET("ranking/winner")
    suspend fun requestWinningRunner(@Header("token") token :String):ResponseData<List<RankingRunner.WinOrLoseRunner>>

    @GET("ranking/loser")
    suspend fun requestLosingRunner(@Header("token") token :String): ResponseData<List<RankingRunner.WinOrLoseRunner>>

    suspend fun requestOpponentRunningDetail(
        @Header("token") token: String,
        @Path("game_idx") game_idx: Int
    ): ResponseData<NetworkOpponentRunningDetail>
}