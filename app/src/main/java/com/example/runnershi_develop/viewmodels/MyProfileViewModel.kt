package com.example.runnershi_develop.viewmodels

import androidx.lifecycle.*
import com.example.runnershi_develop.BuildConfig
import com.example.runnershi_develop.data.User
import com.example.runnershi_develop.data.UserRepository
import kotlinx.coroutines.launch

class MyProfileViewModel internal constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    val user: LiveData<User?> = userRepository.getUser()
    
    init{
        viewModelScope.launch {
            createUser()
        }
    }

    private suspend fun createUser() {
        userRepository.createUser(BuildConfig.USER_ACCESS_KEY)
    }

    fun deleteUser(){
        viewModelScope.launch {
            user.value?.let{userRepository.removeUser(user.value!!)}
        }
    }

}
