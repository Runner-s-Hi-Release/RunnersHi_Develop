package com.example.runnershi_develop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.runnershi_develop.databinding.FragmentSignupTosBinding
import com.example.runnershi_develop.utilities.InjectorUtils
import com.example.runnershi_develop.viewmodels.RankingViewModel

class SignupTosFragment : Fragment() {
    private val signupTosViewModel: SignupTosViewModel by viewModels {
        InjectorUtils.provideRankingViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSignupTosBinding.inflate(inflater, container, false).apply {
            viewModel = signupTosViewModel
            lifecycleOwner = this@SignupTosFragment
        }

        return binding.root
    }
}