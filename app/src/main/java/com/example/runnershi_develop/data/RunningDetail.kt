package com.example.runnershi_develop.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class RunningDetail(
    val month: Int,
    val day: Int,
    @ColumnInfo(name = "start_time")
    val startTime: String,
    @ColumnInfo(name = "end_time")
    val endTime: String
)

data class NetworkRunningDetail(
    val month: Int,
    val day: Int,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("coordinate")
    val coordinates: ArrayList<Coordinate>
)

fun NetworkRunningDetail.asRunningDetail(): RunningDetail{
    return RunningDetail(this.month, this.day, this.startTime, this.endTime)
}

data class MyRunningDetail(
    @SerializedName("pace_minute")
    @ColumnInfo(name = "pace_minute")
    val paceMinute: Int,
    @SerializedName("pace_second")
    @ColumnInfo(name = "pace_second")
    val paceSecond: Int
)

data class OpponentRunningDetail(
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("pace_minute")
    @ColumnInfo(name = "pace_minute")
    val paceMinute: Int,
    @SerializedName("pace_second")
    @ColumnInfo(name = "pace_second")
    val paceSecond: Int
)


data class DataBaseRunningDetail(
    @ColumnInfo(name = "run_idx")
    val runIdx: Int,
    @Embedded
    val runningDetail: RunningDetail?,
    @Embedded(prefix = "my_")
    val myRunningDetail: MyRunningDetail?,
    @Embedded(prefix = "opponent_")
    val opponentRunningDetail: OpponentRunningDetail?
)