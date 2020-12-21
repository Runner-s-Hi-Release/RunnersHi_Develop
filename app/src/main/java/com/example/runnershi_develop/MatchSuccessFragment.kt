package com.example.runnershi_develop

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.runnershi_develop.databinding.FragmentMatchSuccessBinding
import com.example.runnershi_develop.utilities.InjectorUtils
import com.example.runnershi_develop.viewmodels.MatchSuccessViewModel

class MatchSuccessFragment : Fragment() {

    private val matchSuccessViewModel: MatchSuccessViewModel by viewModels{
        InjectorUtils.provideMatchSuccessViewModelFactory(requireContext(), InjectorUtils.provideForegroundServiceFactory(requireContext()))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMatchSuccessBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@MatchSuccessFragment
                viewModel = matchSuccessViewModel
            }

        Handler().postDelayed(
            {
                findNavController().navigate(
                    MatchSuccessFragmentDirections
                        .actionMatchSuccessFragmentToCountDownFragment()
                )
                matchSuccessViewModel.matchSuccessViewModel()

        }, 3000)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    //TODO matching 대기 화면 뒤로가기 버튼
                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return binding.root
    }
}