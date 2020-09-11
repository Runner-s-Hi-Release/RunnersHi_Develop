package com.example.runnershi_develop.data

import androidx.lifecycle.LiveData
import com.example.runnershi_develop.api.RequestInterface
import com.example.runnershi_develop.utilities.apiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository private constructor(
    private val userDao: UserDao,
    private val service: RequestInterface
) {
    suspend fun createUser(token: String) {
        val result = apiCall(call = {service.requestmyProfile(token)})
        withContext(Dispatchers.IO){
            when(result){
                is RequestToServerResult.Success -> userDao.insertUser(result.data.result)
            }
        }
    }

    suspend fun removeUser(user: User) {
        userDao.deleteUser(user)
    }


    fun getUser(): LiveData<User?> {
        return userDao.getUsers()
    }

    companion object {
        @Volatile private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao, service: RequestInterface) =
                instance ?: synchronized(this) {
                    instance ?: UserRepository(userDao, service).also { instance = it }
                }
    }
}