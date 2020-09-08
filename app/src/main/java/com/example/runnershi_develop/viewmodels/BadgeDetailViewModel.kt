package com.example.runnershi_develop.viewmodels

import androidx.lifecycle.*
import com.example.runnershi_develop.BuildConfig
import com.example.runnershi_develop.data.BadgeDetail
import com.example.runnershi_develop.data.BadgeDetailRepository
import kotlinx.coroutines.launch

class BadgeDetailViewModel internal constructor(
    private val badgeDetailRepository: BadgeDetailRepository,
    val index: Int
) : ViewModel() {
    val badgeDetail: MutableLiveData<BadgeDetail> = MutableLiveData()

    init{
        viewModelScope.launch {
            badgeDetail.value = getBadgeDetail()
        }
    }

    private suspend fun getBadgeDetail(): BadgeDetail?{
        return badgeDetailRepository.getBadgeDetail(BuildConfig.USER_ACCESS_KEY, index)
    }

}
