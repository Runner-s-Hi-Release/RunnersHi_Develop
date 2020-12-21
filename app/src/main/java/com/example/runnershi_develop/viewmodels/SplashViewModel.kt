package com.example.runnershi_develop.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.runnershi_develop.data.UserRepository
import kotlinx.coroutines.launch

class SplashViewModel internal constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    fun createUser() {
        viewModelScope.launch {
            userRepository.createUser()
        }
    }
}
