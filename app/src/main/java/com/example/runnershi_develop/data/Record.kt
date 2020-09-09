package com.example.runnershi_develop.data

import com.google.gson.annotations.SerializedName

data class Record (
    @SerializedName("date")
    val createdTime : String,
    @SerializedName("distance")
    val distance : Int,
    @SerializedName("time")
    val time : String,
    @SerializedName("run_idx")
    val runIdx : Int,
    @SerializedName("result")
    val result : Int,
    @SerializedName("game_idx")
    val gameIdx : Int
)