package com.example.runnershi_develop.viewmodels

import androidx.lifecycle.*
import com.example.runnershi_develop.BuildConfig
import com.example.runnershi_develop.data.Running
import com.example.runnershi_develop.data.RunningRepository
import kotlinx.coroutines.launch

class RunningViewModel(
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
            runningRepository.refreshRunnings(BuildConfig.USER_ACCESS_KEY)
        }
    }

    fun displayRecordDetail(running: Running) {
        _navigateToDetail.value = running
    }

    fun displayRecordDetailDone() {
        _navigateToDetail.value = null
    }
}