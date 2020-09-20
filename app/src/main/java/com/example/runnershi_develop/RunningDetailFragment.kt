package com.example.runnershi_develop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.runnershi_develop.databinding.FragmentRunningDetailBinding
import com.example.runnershi_develop.utilities.InjectorUtils
import com.example.runnershi_develop.viewmodels.RunningDetailViewModel

class RunningDetailFragment : Fragment() {

    private val args: RunningDetailFragmentArgs by navArgs()

    private val runningDetailViewModel: RunningDetailViewModel by viewModels {
        InjectorUtils.provideRunningDetailViewModelFactory(requireActivity(), args.running.runIdx, args.running.gameIdx)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentRunningDetailBinding>(
            inflater, R.layout.fragment_running_detail, container, false
        ).apply{
            viewModel = runningDetailViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        binding.btnRecDetailBack.setOnClickListener{view ->
            view.findNavController().navigateUp()
        }

        return binding.root
    }
}