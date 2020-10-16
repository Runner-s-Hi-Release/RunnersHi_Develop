package com.example.runnershi_develop.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.runnershi_develop.data.RankingRepository

class RankingViewModelFactory(
    private val rankingRepository: RankingRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RankingViewModel(
            rankingRepository
        ) as T
    }
}
