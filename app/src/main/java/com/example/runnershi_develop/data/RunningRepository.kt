package com.example.runnershi_develop.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.runnershi_develop.api.RequestToServer
import com.example.runnershi_develop.api.ResultWrapper.*
import com.example.runnershi_develop.api.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class RunningRepository private constructor(private val runningDao: RunningDao) {
    val runnings: LiveData<List<Running>> = Transformations.map(
        runningDao.getRunning()
    ){
        it.asDomainModel()
    }

    suspend fun refreshRunnings(token: String) {
        val callResult = safeApiCall {
            RequestToServer
                .create()
                .requestRunning(token)
        }

        when (callResult) {
            is Success -> {
                val responseData = callResult.value.result
                withContext(Dispatchers.IO){
                    runningDao.insertRunning(responseData.asDatabaseModel())
                }
            }
            is HttpException -> {
                Log.d("Running Repository", "Call Result: Http Exception")
                //todo response 가 200아닐시 무엇을 할지 논의 필요
            }
            is NetworkError -> {
                Log.d("Running Repository", "Call Result: Network Error")
                // todo 통신 실패시 무엇을 할지 논의 필요
            }
            is GenericError -> {
                Log.d("Running Repository", "Call Result: Generic Error")
                //todo
            }
        }
    }

    companion object {
        @Volatile
        private var instance: RunningRepository? = null

        fun getInstance(runningDao: RunningDao) = instance ?: synchronized(this) {
            instance ?: RunningRepository(runningDao).also { instance = it }
        }
    }
}