package com.example.runnershi_develop.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.runnershi_develop.api.RequestToServer
import com.example.runnershi_develop.api.ResultWrapper.*
import com.example.runnershi_develop.api.safeApiCall
import com.example.runnershi_develop.utilities.ACCESS_TOKEN
import com.example.runnershi_develop.utilities.PrefInit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class RunningRepository private constructor(private val runningDao: RunningDao) {
    val runnings: LiveData<List<Running>> = Transformations.map(
        runningDao.getRunnings()
    ){
        it.asDomainModel()
    }

    suspend fun refreshRunnings() {
        when (val callResult = safeApiCall {
            RequestToServer.service.requestRunning(PrefInit.prefs.getString(ACCESS_TOKEN, ""))
        }) {
            is Success -> {
                val responseData = callResult.value.data
                withContext(Dispatchers.IO) {
                    //TODO 서버 오류로 인하여 주석처리
//                    runningDao.insertRunning(responseData.asDatabaseModel())
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

    suspend fun getRunningDetail(runIdx: Int): NetworkRunningDetail? {
        val callResult = safeApiCall {
            RequestToServer.service.requestRunningDetail(PrefInit.prefs.getString(ACCESS_TOKEN, ""), runIdx)
        }
        when (callResult) {
            is Success -> {
                return callResult.value.data
            }
        }
        return null
    }

    suspend fun getMyRunningDetail(runIdx: Int): NetworkMyRunningDetail? {
        val callResult = safeApiCall {
            RequestToServer.service.requestMyRunningDetail(PrefInit.prefs.getString(ACCESS_TOKEN, ""), runIdx)
        }
        when (callResult) {
            is Success -> {
                return callResult.value.data
            }
        }
        return null
    }

    suspend fun getOpponentRunningDetail(gameIdx: Int): NetworkOpponentRunningDetail?  {
        val callResult = safeApiCall {
            RequestToServer.service.requestOpponentRunningDetail(PrefInit.prefs.getString(ACCESS_TOKEN, ""), gameIdx)
        }
        when (callResult) {
            is Success -> {
                return callResult.value.data
            }
        }
        return null
    }

    suspend fun updateRunningDetail(
        runIdx: Int, runningDetail: RunningDetail?, myRunningDetail: MyRunningDetail?,
        opponentRunningDetail: OpponentRunningDetail?
    ) {
        if (runningDetail != null && myRunningDetail != null)
            runningDao.updateRunning(
                DataBaseRunningDetail(
                    runIdx = runIdx,
                    runningDetail = runningDetail,
                    myRunningDetail = myRunningDetail,
                    opponentRunningDetail = opponentRunningDetail
                )
            )
    }

    fun getRunning(runIdx: Int): LiveData<DatabaseRunning> {
        return runningDao.getRunning(runIdx)
    }

    companion object {
        @Volatile
        private var instance: RunningRepository? = null

        fun getInstance(runningDao: RunningDao) = instance ?: synchronized(this) {
            instance ?: RunningRepository(runningDao).also { instance = it }
        }
    }
}