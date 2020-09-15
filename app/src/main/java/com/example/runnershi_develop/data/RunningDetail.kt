package com.example.runnershi_develop.data

import com.google.gson.annotations.SerializedName

data class RunningDetail(
    @SerializedName("month")
    val month: Int,
    @SerializedName("day")
    val day: Int,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("coordinate")
    val coordinate: List<Coordinate>
)