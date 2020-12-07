package com.example.runnershi_develop.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.runnershi_develop.data.RaceRepository

class RaceViewModelFactory(
    private val raceRepository: RaceRepository,
    private val runIdx: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RaceViewModel(raceRepository, runIdx) as T
    }
}