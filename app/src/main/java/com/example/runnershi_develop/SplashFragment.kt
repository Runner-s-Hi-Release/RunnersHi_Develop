package com.example.runnershi_develop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.runnershi_develop.databinding.FragmentSplashBinding
import com.example.runnershi_develop.extension.animatorListener

class SplashFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentSplashBinding>(inflater,
            R.layout.fragment_splash,container,false)

        binding.splashStart.animatorListener {
            view?.findNavController()?.navigate(SplashFragmentDirections.actionSplashFragmentToHomeViewPagerFragment())
        }

        return binding.root
    }
}