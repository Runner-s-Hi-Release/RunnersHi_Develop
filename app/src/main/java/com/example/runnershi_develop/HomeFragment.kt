package com.example.runnershi_develop

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.runnershi_develop.databinding.FragmentHomeBinding
import com.example.runnershi_develop.utilities.PermissionHandler


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false).apply{
            lifecycleOwner = this@HomeFragment
            btnHomeRunnow.setOnClickListener {

                val ifPermissionGranted = PermissionHandler.checkPermission(
                    requireActivity(),
                    listOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    REQUEST_LOCATION_PERMISSION
                )
                if(ifPermissionGranted) {
                    findNavController().navigate(
                        HomeViewPagerFragmentDirections
                            .actionHomeViewPagerFragmentToMatchingGoalFragment()
                    )
                }
            }
        }
        return binding.root
    }
    companion object{
        private const val REQUEST_LOCATION_PERMISSION = 1000
    }
}



