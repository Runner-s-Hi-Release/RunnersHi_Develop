package com.example.runnershi_develop.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.runnershi_develop.api.RequestToServer
import com.example.runnershi_develop.api.ResultWrapper
import com.example.runnershi_develop.api.safeApiCall
import com.example.runnershi_develop.utilities.ACCESS_TOKEN
import com.example.runnershi_develop.utilities.PrefInit
import com.example.runnershi_develop.utilities.USER_ID
import retrofit2.HttpException

class UserRepository private constructor(
    private val userDao: UserDao
) {

    suspend fun createUser() {
        when (val callResult = safeApiCall {
            RequestToServer.service
                .requestToken(UUID(PrefInit.prefs.getString(USER_ID, "")))
        }) {
            is ResultWrapper.Success -> {
                PrefInit.prefs.setString(ACCESS_TOKEN, callResult.value.data.accessToken)
                userDao.insertUser(callResult.value.data)
                Log.d("Running Repository", "Success")
            }
            is HttpException -> {
                Log.d("Running Repository", "Call Result: Http Exception")
            }
            is ResultWrapper.NetworkError -> {
                Log.d("Running Repository", "Call Result: Network Error")
            }
            is ResultWrapper.GenericError -> {
                Log.d("Running Repository", "Call Result: Generic Error")
            }
        }
    }

    suspend fun removeUser(user: User) {
        userDao.deleteUser(user)
    }


    fun getUser(): LiveData<User?> {
        return userDao.getUser()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userDao).also { instance = it }
            }
    }
}