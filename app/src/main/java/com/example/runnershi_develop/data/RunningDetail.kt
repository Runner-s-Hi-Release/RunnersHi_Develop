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

fun NetworkRunningDetail.asRunningDetail(): RunningDetail {
    return RunningDetail(this.month, this.day, this.startTime, this.endTime)
}

data class MyRunningDetail(
    @ColumnInfo(name = "pace_minute")
    val paceMinute: Int,
    @ColumnInfo(name = "pace_second")
    val paceSecond: Int
)

data class NetworkMyRunningDetail(
    @SerializedName("pace_minute")
    val paceMinute: Int,
    @SerializedName("pace_second")
    val paceSecond: Int
)

fun NetworkMyRunningDetail.asMyRunningDetail(): MyRunningDetail {
    return MyRunningDetail(this.paceMinute, this.paceSecond)
}

data class OpponentRunningDetail(
    val nickname: String,
    val distance: Int,
    @ColumnInfo(name = "pace_minute")
    val paceMinute: Int,
    @ColumnInfo(name = "pace_second")
    val paceSecond: Int
)

data class NetworkOpponentRunningDetail(
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("pace_minute")
    val paceMinute: Int,
    @SerializedName("pace_second")
    val paceSecond: Int
)

fun NetworkOpponentRunningDetail.asOpponentRunningDetail(): OpponentRunningDetail {
    return OpponentRunningDetail(this.nickname, this.distance, this.paceMinute, this.paceSecond)
}

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