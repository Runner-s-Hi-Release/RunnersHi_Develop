package com.example.runnershi_develop.data

import com.example.runnershi_develop.api.RequestInterface
import com.example.runnershi_develop.utilities.apiCall

class BadgeDetailRepository private constructor(
    private val service: RequestInterface
) {
    suspend fun getBadgeDetail(token: String, index: Int): BadgeDetail? {
        when(val result = apiCall(call = {service.requestBadgeDetail(token, index)})){
            is RequestToServerResult.Success -> {
                return result.data.result
            }
        }
        return null
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: BadgeDetailRepository? = null

        fun getInstance(service: RequestInterface) =
            instance ?: synchronized(this) {
                instance ?: BadgeDetailRepository(service).also { instance = it }
            }
    }
}