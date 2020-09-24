package com.example.runnershi_develop.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunningDao {
    @Query("SELECT * FROM running")
    fun getRunnings(): LiveData<List<DatabaseRunning>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRunning(runnings: List<DatabaseRunning>)

    @Update(entity =DatabaseRunning::class)
    suspend fun updateRunning(databaseRunningDetail: DataBaseRunningDetail)

    @Query("SELECT * FROM running WHERE run_idx =:runIdx")
    fun getRunning(runIdx : Int): LiveData<DatabaseRunning>
}