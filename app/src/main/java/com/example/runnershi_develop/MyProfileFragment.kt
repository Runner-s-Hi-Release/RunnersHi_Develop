package com.example.runnershi_develop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.runnershi_develop.adapters.MyProfileBadgeAdapter
import com.example.runnershi_develop.databinding.FragmentMyProfileBinding
import com.example.runnershi_develop.utilities.InjectorUtils
import com.example.runnershi_develop.viewmodels.MyProfileViewModel

class MyProfileFragment : Fragment() {
    private val myProfileViewModel: MyProfileViewModel by viewModels {
        InjectorUtils.provideMyProfileViewModelFactory(requireContext())
    }
    private val myProfileBadgeAdapter: MyProfileBadgeAdapter = MyProfileBadgeAdapter { badge ->
        myProfileViewModel.displayBadgeDetail(badge)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentMyProfileBinding>(
            inflater, R.layout.fragment_my_profile, container, false
        ).apply {
            viewModel = myProfileViewModel
            lifecycleOwner = viewLifecycleOwner
            rvBadge.adapter = myProfileBadgeAdapter
            btnLogout.setOnClickListener {
                myProfileViewModel.deleteUser()
            }
        }

        myProfileViewModel.navigateToBadgeDetail.observe(viewLifecycleOwner, Observer { badge ->
            if (badge != null) {
                this.findNavController().navigate(
                    HomeViewPagerFragmentDirections
                        .actionViewPagerFragmentToFragmentBadgeDetail(badge)
                )
                myProfileViewModel.displayBadgeDetailDone()
            }
        })
        return binding.root
    }
}