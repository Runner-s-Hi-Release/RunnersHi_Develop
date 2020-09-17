package com.example.runnershi_develop.data

import com.example.runnershi_develop.api.RequestToServer
import com.example.runnershi_develop.api.ResultWrapper
import com.example.runnershi_develop.api.safeApiCall

class BadgeDetailRepository private constructor(
) {
    suspend fun getBadgeDetail(token: String, index: Int): BadgeDetail? {
        when(val callResult = safeApiCall(call = { RequestToServer.service.requestBadgeDetail(token, index)})){
            is ResultWrapper.Success -> {
                return callResult.value.result
            }
        }
        return null
    }
    companion object {
        // For Singleton instantiation
        @Volatile private var instance: BadgeDetailRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: BadgeDetailRepository().also { instance = it }
            }
    }
}