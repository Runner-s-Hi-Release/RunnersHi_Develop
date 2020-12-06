package com.example.runnershi_develop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.runnershi_develop.adapters.RankingRunnerAdapter
import com.example.runnershi_develop.databinding.FragmentRankingBinding
import com.example.runnershi_develop.utilities.InjectorUtils
import com.example.runnershi_develop.viewmodels.RankingViewModel

class RankingFragment : Fragment() {
    private val rankingViewModel: RankingViewModel by viewModels {
        InjectorUtils.provideRankingViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRankingBinding.inflate(inflater, container, false).apply {
            viewModel = rankingViewModel
            lifecycleOwner = this@RankingFragment
            rvRankingHeavyRunner.adapter = RankingRunnerAdapter()
            rvRankingWinningRunner.adapter = RankingRunnerAdapter()
            rvRankingLosingRunner.adapter = RankingRunnerAdapter()
        }

        binding.apply{
            refreshLayout.setOnRefreshListener {
                viewModel!!.onRefreshed()
                refreshLayout.isRefreshing = false
            }
        }

        return binding.root
    }
}