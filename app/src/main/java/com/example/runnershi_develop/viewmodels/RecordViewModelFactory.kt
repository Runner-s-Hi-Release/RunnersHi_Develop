package com.example.runnershi_develop.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.runnershi_develop.data.RunningRepository
import java.lang.IllegalArgumentException

class RecordViewModelFactory(
    private val runningRepository: RunningRepository)
    : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RecordViewModel::class.java)){
            return RecordViewModel(runningRepository) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }

}