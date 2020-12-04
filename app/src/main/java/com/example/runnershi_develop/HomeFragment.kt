package com.example.runnershi_develop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.runnershi_develop.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false).apply{
            btnHomeRunnow.setOnClickListener {
                findNavController().navigate(
                    HomeViewPagerFragmentDirections
                        .actionHomeViewPagerFragmentToMatchingGoalFragment()
                )
            }
        }
        return binding.root
    }
}



