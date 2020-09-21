package com.example.runnershi_develop.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunningDao {
    @Query("SELECT * FROM running")
    fun getRunning(): LiveData<List<DatabaseRunning>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRunning(runnings: List<DatabaseRunning>)
}