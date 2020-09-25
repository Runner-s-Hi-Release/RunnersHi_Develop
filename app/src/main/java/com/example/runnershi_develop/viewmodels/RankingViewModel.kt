package com.example.runnershi_develop.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runnershi_develop.BuildConfig
import com.example.runnershi_develop.data.*
import kotlinx.coroutines.Dispatchers
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

    private fun getRankingRunner(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _heavyRunners.postValue(rankingRepository.getHeavyRunner(BuildConfig.USER_ACCESS_KEY))
                _winningRunners.postValue(rankingRepository.getWinningRunner(BuildConfig.USER_ACCESS_KEY))
                _losingRunners.postValue(rankingRepository.getLosingRunner(BuildConfig.USER_ACCESS_KEY))
                _status.postValue(RankingApiStatus.DONE)
            } catch (e: Exception) {
                _status.postValue(RankingApiStatus.ERROR)
                _heavyRunners.postValue(ArrayList())
                _winningRunners.postValue(ArrayList())
                _losingRunners.postValue(ArrayList())
            }
        }
    }

}