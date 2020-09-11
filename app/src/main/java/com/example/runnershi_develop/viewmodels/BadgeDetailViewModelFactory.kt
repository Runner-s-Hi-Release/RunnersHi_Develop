package com.example.runnershi_develop.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.runnershi_develop.data.BadgeDetailRepository

class BadgeDetailViewModelFactory(
    private val badgeDetailRepository: BadgeDetailRepository,
    private val index: Int
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BadgeDetailViewModel(
            badgeDetailRepository, index
        ) as T
    }
}
