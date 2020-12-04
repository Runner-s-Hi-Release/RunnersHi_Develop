package com.example.runnershi_develop.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runnershi_develop.data.MatchingRepository
import com.example.runnershi_develop.data.RunningStart
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class MatchingViewModel internal constructor(
    private val matchingRepository: MatchingRepository,
    private val runningStart: RunningStart
) : ViewModel() {

    val success: LiveData<Boolean>
        get() = matchingRepository.getSuccess()

    fun startForegroundService(){
        viewModelScope.launch{
            matchingRepository.startForegroundService(runningStart)
        }
    }

    fun stopForegroundService(){
        matchingRepository.stopForegroundService()
    }
}