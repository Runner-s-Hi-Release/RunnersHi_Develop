package com.example.runnershi_develop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.runnershi_develop.databinding.FragmentSplashBinding
import com.example.runnershi_develop.extension.animatorListener
import com.example.runnershi_develop.utilities.*
import com.example.runnershi_develop.viewmodels.SplashViewModel
import java.util.*

class SplashFragment : Fragment() {
    private val splashViewModel: SplashViewModel by viewModels {
        InjectorUtils.provideSplashViewModelFactory(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSplashBinding>(
            inflater,
            R.layout.fragment_splash, container, false
        ).apply {
            lifecycleOwner = this@SplashFragment
        }

        if (PrefInit.prefs.getString(FIRST_RUN, "y") == "y") {
            PrefInit.prefs.setString(USER_ID, UUID.randomUUID().toString())
        }
        splashViewModel.createUser()

        binding.splashStart.animatorListener {
            if (PrefInit.prefs.getString(FIRST_RUN, "y") == "y") {
                PrefInit.prefs.setString(FIRST_RUN, "n")
                view?.findNavController()
                    ?.navigate(SplashFragmentDirections.actionSplashFragmentToOnBoardFragment())
            } else {
                view?.findNavController()
                    ?.navigate(SplashFragmentDirections.actionSplashFragmentToHomeViewPagerFragment())
            }
        }

        return binding.root
    }
}