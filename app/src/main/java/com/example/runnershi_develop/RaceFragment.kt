package com.example.runnershi_develop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.runnershi_develop.databinding.FragmentRaceBinding
import com.example.runnershi_develop.utilities.InjectorUtils
import com.example.runnershi_develop.viewmodels.RaceViewModel

class RaceFragment : Fragment() {
    private val args: RaceFragmentArgs by navArgs()
    private val raceViewModel: RaceViewModel by viewModels {
        InjectorUtils.provideRaceViewModelFactory(args.runIdx)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRaceBinding.inflate(inflater, container, false).apply {
            viewModel = raceViewModel
            lifecycleOwner = this@RaceFragment
        }
        return binding.root
    }
}