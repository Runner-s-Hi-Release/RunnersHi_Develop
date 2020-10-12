package com.example.runnershi_develop.data

import com.example.runnershi_develop.api.RequestToServer
import com.example.runnershi_develop.api.ResultWrapper
import com.example.runnershi_develop.api.safeApiCall

class RankingRepository {
    suspend fun getWinningRunner(token: String): List<RankingRunner.WinOrLoseRunner>? {
        val callResult = safeApiCall {
            RequestToServer
                .service
                .requestWinningRunner(token)
        }
        when (callResult) {
            is ResultWrapper.Success -> {
                return callResult.value.result
            }
            is ResultWrapper.NetworkError -> {
                throw error("NETWORK ERROR")
            }
        }
        return null
    }

    suspend fun getLosingRunner(token: String): List<RankingRunner.WinOrLoseRunner>? {
        val callResult = safeApiCall {
            RequestToServer
                .service
                .requestLosingRunner(token)
        }
        when (callResult) {
            is ResultWrapper.Success -> {
                return callResult.value.result
            }
            is ResultWrapper.NetworkError -> {
                throw error("NETWORK ERROR")
            }
        }
        return null
    }

    suspend fun getHeavyRunner(token: String): List<RankingRunner.HeavyRunner>? {
        val callResult = safeApiCall {
            RequestToServer
                .service
                .requestHeavyRunner(token)
        }
        when (callResult) {
            is ResultWrapper.Success -> {
                return callResult.value.result
            }
            is ResultWrapper.NetworkError -> {
                throw error("NETWORK ERROR")
            }
        }
        return null
    }

    companion object {
        @Volatile
        private var instance: RankingRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: RankingRepository().also { instance = it }
            }
    }
}