package com.example.runnershi_develop.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runnershi_develop.BuildConfig
import com.example.runnershi_develop.data.*
import kotlinx.coroutines.launch

enum class RankingApiStatus { DONE, ERROR }

class RankingViewModel internal constructor(
    private val rankingRepository: RankingRepository
) : ViewModel() {
    private val _status = MutableLiveData<RankingApiStatus>()
    private val _heavyRunners = MutableLiveData<List<RankingRunner.HeavyRunner>>()
    private val _winningRunners = MutableLiveData<List<RankingRunner.WinOrLoseRunner>>()
    private val _losingRunners = MutableLiveData<List<RankingRunner.WinOrLoseRunner>>()
    val status: LiveData<RankingApiStatus>
        get() = _status
    val winningRunners: LiveData<List<RankingRunner.WinOrLoseRunner>>
        get() = _winningRunners
    val losingRunners: LiveData<List<RankingRunner.WinOrLoseRunner>>
        get() = _losingRunners
    val heavyRunners: LiveData<List<RankingRunner.HeavyRunner>>
        get() = _heavyRunners

    init{
        getRankingRunner()
    }

    fun getRankingRunner(){
        viewModelScope.launch {
            try {
                _heavyRunners.value = rankingRepository.getHeavyRunner(BuildConfig.USER_ACCESS_KEY)
                _winningRunners.value = rankingRepository.getWinningRunner(BuildConfig.USER_ACCESS_KEY)
                _losingRunners.value=rankingRepository.getLosingRunner(BuildConfig.USER_ACCESS_KEY)
                _status.value = RankingApiStatus.DONE
            } catch (e: Exception) {
                _status.value = RankingApiStatus.ERROR
                _heavyRunners.value = ArrayList()
                _winningRunners.value = ArrayList()
                _losingRunners.value = ArrayList()
            }
        }
    }
}