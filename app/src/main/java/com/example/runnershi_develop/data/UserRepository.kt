package com.example.runnershi_develop.data

import androidx.lifecycle.LiveData
import com.example.runnershi_develop.api.RequestToServer
import com.example.runnershi_develop.api.ResultWrapper
import com.example.runnershi_develop.api.safeApiCall
import com.example.runnershi_develop.utilities.ACCESS_TOKEN
import com.example.runnershi_develop.utilities.PrefInit
import com.example.runnershi_develop.utilities.USER_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository private constructor(
    private val userDao: UserDao
) {

    suspend fun createUser() {
        val result = safeApiCall(call = {RequestToServer.service.requestToken(UUID(PrefInit.prefs.getString(
            USER_ID, "")))})
        withContext(Dispatchers.IO) {
            when (result) {
                is ResultWrapper.Success -> {
                    PrefInit.prefs.setString(ACCESS_TOKEN, result.value.data.accessToken)
                    userDao.insertUser(result.value.data)

                }
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