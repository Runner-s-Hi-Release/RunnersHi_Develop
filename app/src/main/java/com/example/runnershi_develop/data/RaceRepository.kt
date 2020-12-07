package com.example.runnershi_develop.data

import com.example.runnershi_develop.api.RequestToServer
import com.example.runnershi_develop.api.ResultWrapper
import com.example.runnershi_develop.api.safeApiCall

class RaceRepository {
    suspend fun getMyRaceRanking(token: String, runIdx :Int): Int? {
        val callResult = safeApiCall {
            RequestToServer
                .service
                .requestRaceRanking(token,runIdx)
        }
        when (callResult) {
            is ResultWrapper.Success -> {
                return callResult.value.data.ranking
            }
            is ResultWrapper.NetworkError -> {
                throw error("NETWORK ERROR")
            }
        }
        return null
    }

    companion object {
        @Volatile
        private var instance: RaceRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: RaceRepository().also { instance = it }
            }
    }
}