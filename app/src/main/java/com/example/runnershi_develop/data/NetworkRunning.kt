package com.example.runnershi_develop.data

import com.google.gson.annotations.SerializedName


data class NetworkRunning(
    @SerializedName("date")
    val date: String,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("time")
    val time: String,
    @SerializedName("run_idx")
    val runIdx: Int,
    @SerializedName("result")
    val result: Int,
    @SerializedName("game_idx")
    val gameIdx: Int
)

fun List<NetworkRunning>.asDatabaseModel(): List<DatabaseRunning> {
    return this.map {
        DatabaseRunning(
            runIdx = it.runIdx,
            gameIdx = it.gameIdx,
            date = it.date,
            myDistance = it.distance,
            time = it.time,
            result = it.result
        )
    }
}