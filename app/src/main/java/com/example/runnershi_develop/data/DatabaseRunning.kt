package com.example.runnershi_develop.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Opponent(
    @ColumnInfo(name = "opponent_nickname")
    val nickName: String,
    @ColumnInfo(name = "opponent_distance")
    val distance: Int,
    @ColumnInfo(name = "opponent_pace_minute")
    val paceMinute: Int,
    @ColumnInfo(name = "opponent_pace_second")
    val paceSecond: Int
)

@Entity(tableName = "running")
data class DatabaseRunning(
    @PrimaryKey
    @ColumnInfo(name = "run_idx")
    val runIdx: Int,
    @ColumnInfo(name = "game_idx")
    val gameIdx: Int,
    val date: String,
    val time: String,
    val result: Int,
    @ColumnInfo(name = "my_distance")
    val myDistance: Int,
    @ColumnInfo(name = "my_pace_minute")
    val myPaceMinute: Int? = null,
    @ColumnInfo(name = "my_pace_second")
    val myPaceSecond: Int? = null,
    @Embedded val opponent: Opponent? = null
)

fun List<DatabaseRunning>.asDomainModel(): List<Running> {
    return this.map {
        Running(
            date = it.date,
            distance = it.myDistance,
            time = it.time,
            gameIdx = it.gameIdx,
            runIdx = it.runIdx,
            result = it.result
        )
    }
}




