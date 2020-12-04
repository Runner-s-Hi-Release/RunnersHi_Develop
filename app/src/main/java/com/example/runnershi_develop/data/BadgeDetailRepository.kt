package com.example.runnershi_develop.data

import com.example.runnershi_develop.api.RequestToServer.service
import com.example.runnershi_develop.api.ResultWrapper
import com.example.runnershi_develop.api.safeApiCall
import com.example.runnershi_develop.utilities.ACCESS_TOKEN
import com.example.runnershi_develop.utilities.PrefInit

class BadgeDetailRepository private constructor(
) {
    suspend fun getBadgeDetail(index: Int): BadgeDetail? {
        when(val callResult = safeApiCall(call = { service.requestBadgeDetail(PrefInit.prefs.getString(ACCESS_TOKEN, ""), index)})){
            is ResultWrapper.Success -> {
                return callResult.value.data
            }
        }
        return null
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: BadgeDetailRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: BadgeDetailRepository().also { instance = it }
            }
    }
}