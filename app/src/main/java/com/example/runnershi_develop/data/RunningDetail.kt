package com.example.runnershi_develop.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

data class RunningDetail(
    @SerializedName("month")
    val month: Int,
    @SerializedName("day")
    val day: Int,
    @SerializedName("start_time")
    @ColumnInfo(name = "start_time")
    val startTime: String,
    @SerializedName("end_time")
    @ColumnInfo(name = "end_time")
    val endTime: String
//    @SerializedName("coordinate")
//    @Ignore val coordinate: List<Coordinate>
)

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

@Entity
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