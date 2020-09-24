package com.example.runnershi_develop.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.runnershi_develop.data.RunningRepository
import java.lang.IllegalArgumentException

class RunningViewModelFactory(
    private val runningRepository: RunningRepository)
    : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RunningViewModel::class.java)){
            return RunningViewModel(runningRepository) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }

}