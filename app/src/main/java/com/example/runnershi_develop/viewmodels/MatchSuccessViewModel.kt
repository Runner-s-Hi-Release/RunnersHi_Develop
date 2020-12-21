package com.example.runnershi_develop.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.runnershi_develop.data.MatchingRepository
import com.example.runnershi_develop.data.Opponent

@RequiresApi(Build.VERSION_CODES.O)
class MatchSuccessViewModel internal constructor(
    private val matchingRepository: MatchingRepository
) : ViewModel() {
    val opponent: LiveData<Opponent>
        get() = matchingRepository.getOpponent()

    fun matchSuccessViewModel(){
        matchingRepository.deleteOpponent()
    }
}