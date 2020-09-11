package com.example.runnershi_develop.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.runnershi_develop.data.NetworkRunning
import com.example.runnershi_develop.data.Running
import com.example.runnershi_develop.data.RunningRepository
import kotlinx.coroutines.launch

class RecordViewModel(
    private val runningRepository: RunningRepository)
    : ViewModel() {
    val runnings = runningRepository.runnings

    private val _navigateToDetail = MutableLiveData<Running>()
    val navigateToDetail: LiveData<Running>
        get() = _navigateToDetail

    init{
        refreshDataFromRepository()
    }

    fun refreshDataFromRepository(){
        viewModelScope.launch{
            runningRepository.refreshRunnings("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6InRlc3QxIiwicGFzc3dvcmQiOiJ0ZXN0MSIsInRva2VuIjoidG9rZW4iLCJpYXQiOjE1OTk4MDk2MTUsImV4cCI6MTU5OTg0NTYxNX0.G5toh-jLeYYdFkkqbXhqPJqvxu5b0wtgyiDgB-lqzTE")
        }
    }

    fun displayRecordDetail(running: Running) {
        _navigateToDetail.value = running
    }

    fun displayRecordDetailDone() {
        _navigateToDetail.value = null
    }
}