package com.example.runnershi_develop.data

import com.google.gson.annotations.SerializedName

data class MyRunningDetail(
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("time")
    val time: String,
    @SerializedName("pace_minute")
    val paceMinute: Int,
    @SerializedName("pace_second")
    val paceSecond: Int,
    @SerializedName("result")
    val result: Int //승패여부
)