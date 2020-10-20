package com.example.runnershi_develop.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.runnershi_develop.data.RunningRepository
import java.lang.IllegalArgumentException

class RunningDetailViewModelFactory(
    private val runningRepository: RunningRepository,
    private val runIdx: Int,
    private val gameIdx: Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RunningDetailViewModel::class.java)) {
            return RunningDetailViewModel(runningRepository, runIdx, gameIdx) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }

}