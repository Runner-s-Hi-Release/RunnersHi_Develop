package com.example.runnershi_develop.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.runnershi_develop.data.MatchingRepository
import com.example.runnershi_develop.data.RunningStart

class MatchingViewModelFactory(
    private val matchingRepository: MatchingRepository,
    private val runningStart: RunningStart
) : ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.O)
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MatchingViewModel(
            matchingRepository, runningStart
        ) as T
    }
}