package com.example.runnershi_develop.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runnershi_develop.data.MatchingRepository
import com.example.runnershi_develop.data.Opponent
import com.example.runnershi_develop.data.RunningStart
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.timerTask

@RequiresApi(Build.VERSION_CODES.O)
class MatchingViewModel internal constructor(
    private val matchingRepository: MatchingRepository,
    private val runningStart: RunningStart
) : ViewModel() {

    private var timer = Timer()

    val opponent: LiveData<Opponent>
        get() = matchingRepository.getOpponent()

    val matchingTime: MutableLiveData<Int> = MutableLiveData()

    fun startForegroundService(){
        viewModelScope.launch{
            matchingRepository.startForegroundService(runningStart)
        }
    }

    fun stopForegroundService(){
        matchingRepository.stopForegroundService()
    }

    private fun stopTimerTask() {
        timer?.cancel()
        timer?.purge()
        timer = Timer()
    }

    fun startTimerTask() {
        stopTimerTask()
        matchingTime.value = 0
        timer.schedule(timerTask{
            matchingTime.postValue(matchingTime.value!! + 1)
        }, 0, 1000)
    }
}