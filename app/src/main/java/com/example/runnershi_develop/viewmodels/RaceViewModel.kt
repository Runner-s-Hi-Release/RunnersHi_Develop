package com.example.runnershi_develop.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runnershi_develop.BuildConfig
import com.example.runnershi_develop.data.RaceRepository
import com.example.runnershi_develop.data.RankingRunner
import kotlinx.coroutines.launch

class RaceViewModel internal constructor(
    private val raceRepository: RaceRepository,
    private val runIdx: Int
) : ViewModel() {
    private val _myRanking = MutableLiveData<Int>()
    private val _opponentRanking = MutableLiveData<Int>()
    val myRanking: LiveData<Int>
        get() = _myRanking
    val opponentRanking: LiveData<Int>
        get() = _opponentRanking

    init {
        getRaceRanking()
    }

    fun getRaceRanking() {
        viewModelScope.launch {
            _myRanking.value =
                raceRepository.getMyRaceRanking(BuildConfig.USER_ACCESS_KEY, runIdx)
            _opponentRanking.value =
                if (_myRanking == MutableLiveData(RANKING_WINNER))
                    RANKING_LOSER
                else
                    RANKING_WINNER
        }
    }

    companion object {
        private const val RANKING_WINNER = 1
        private const val RANKING_LOSER = 2
    }
}