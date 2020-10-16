package com.example.runnershi_develop.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.runnershi_develop.api.RequestToServer.service
import com.example.runnershi_develop.api.ResultWrapper
import com.example.runnershi_develop.api.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository private constructor(
    private val userDao: UserDao
) {
    suspend fun createUser(token: String) {
        val result = safeApiCall(call = {service.requestmyProfile(token)})
        withContext(Dispatchers.IO){
            when(result){
                is ResultWrapper.Success -> {
                    Log.d("result", result.value.result.nickname)
                    userDao.insertUser(result.value.result)

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
        @Volatile private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao) =
                instance ?: synchronized(this) {
                    instance ?: UserRepository(userDao).also { instance = it }
                }
    }
}