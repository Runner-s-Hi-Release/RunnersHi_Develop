package com.example.runnershi_develop.viewmodels

import androidx.lifecycle.*
import com.example.runnershi_develop.data.BadgeDetail
import com.example.runnershi_develop.data.BadgeDetailRepository
import kotlinx.coroutines.launch

class BadgeDetailViewModel internal constructor(
    private val badgeDetailRepository: BadgeDetailRepository,
    val index: Int
) : ViewModel() {
    private val _badgeDetail: MutableLiveData<BadgeDetail?> = MutableLiveData()
    val badgeDetail: LiveData<BadgeDetail?>
        get() = _badgeDetail

    init {
        getBadgeDetail()
    }

    private fun getBadgeDetail() {
        viewModelScope.launch {
            _badgeDetail.value = badgeDetailRepository.getBadgeDetail(index)
        }
    }
}
