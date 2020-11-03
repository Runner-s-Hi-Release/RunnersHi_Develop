package com.example.runnershi_develop

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.runnershi_develop.databinding.FragmentRaceBinding

class RaceFragment : Fragment() {
    private val raceViewModel: RaceViewModel = RaceViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRaceBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@RaceFragment
        }

        var winnerMove = 0f
        var loserMove = 0f
        var winnerSrc = 0
        var loserSrc = 0

        binding.btnRunLock.setOnClickListener {
            if (loserMove == 0f) {
                loserMove = -280f
                loserSrc = R.drawable.icon_run_win
            } else {
                loserMove = 0f
                loserSrc = R.drawable.icon_run_lose
            }

            if (winnerMove == 0f) {
                winnerMove = 280f
                winnerSrc = R.drawable.icon_run_lose
            } else {
                winnerMove = 0f
                winnerSrc = R.drawable.icon_run_win
            }

            ObjectAnimator.ofFloat(binding.raceLoserProfile, "translationY", loserMove).apply {
                duration = 300
                start()
                binding.imgvLoserRaking.setImageResource(loserSrc)
            }

            ObjectAnimator.ofFloat(binding.raceWinnerProfile, "translationY", winnerMove)
                .apply {
                    duration = 300
                    start()
                    binding.imgvWinnerRaking.setImageResource(winnerSrc)
                }
        }

        return binding.root
    }
}