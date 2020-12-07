package com.example.runnershi_develop.adapters

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.runnershi_develop.R
import com.example.runnershi_develop.extension.move
import com.example.runnershi_develop.extension.useGlide

@BindingAdapter("raceRanking")
fun bindRaceRanking(view: ImageView, ranking: Int) {
    if (ranking == 1) {
        view.useGlide(R.drawable.icon_run_win)
    } else if (ranking == 2) {
        view.useGlide(R.drawable.icon_run_lose)
    }
}

@BindingAdapter("raceRanking")
fun bindRaceRanking(view: ConstraintLayout, ranking: Int) {
    if (ranking == 1) {
        RaceProfileMover.moveToWinner(view)
    } else if (ranking == 2) {
        RaceProfileMover.moveToLoser(view)
    }
}

object RaceProfileMover {
    private const val MOVE_WINNER = -280f
    private const val MOVE_RESET = 0f
    private const val MOVE_LOSER = 280f

    fun moveToWinner(raceProfile: ConstraintLayout) {
        raceProfile.move(MOVE_RESET)
        raceProfile.move(MOVE_WINNER)
    }

    fun moveToLoser(raceProfile: ConstraintLayout) {
        raceProfile.move(MOVE_RESET)
        raceProfile.move(MOVE_LOSER)
    }
}