package com.example.runnershi_develop

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.runnershi_develop.databinding.FragmentMatchingBinding
import com.example.runnershi_develop.utilities.InjectorUtils
import com.example.runnershi_develop.viewmodels.MatchingViewModel


class MatchingFragment : Fragment() {

    private val args: MatchingFragmentArgs by navArgs()

    private val matchingViewModel: MatchingViewModel by viewModels{
        InjectorUtils.provideMatchingViewModelFactory(args.RunningStart, requireContext(), InjectorUtils.provideForegroundServiceFactory(requireContext()))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMatchingBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@MatchingFragment
                btnLoginConfirm.setOnClickListener{
                    matchingViewModel.stopForegroundService()
                    findNavController().navigateUp()
                }
            }
        matchingViewModel.startForegroundService()

        matchingViewModel.success?.observe(viewLifecycleOwner, Observer {
        })

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    matchingViewModel.stopForegroundService()
                    findNavController().navigateUp()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)



        return binding.root
    }

}