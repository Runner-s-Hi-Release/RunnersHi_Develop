package com.example.runnershi_develop.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    @Embedded
    val runningDetail: RunningDetail? = null,
    @Embedded(prefix = "my_")
    val myRunningDetail: MyRunningDetail? = null,
    @Embedded(prefix = "opponent_")
    val opponentRunningDetail: OpponentRunningDetail? = null
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





