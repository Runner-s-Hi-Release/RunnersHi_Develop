package com.example.runnershi_develop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.runnershi_develop.adapters.MyProfileBadgeAdapter
import com.example.runnershi_develop.databinding.FragmentMyProfileBinding
import com.example.runnershi_develop.utilities.InjectorUtils
import com.example.runnershi_develop.viewmodels.MyProfileViewModel
import androidx.lifecycle.observe

class MyProfileFragment : Fragment() {

    private val myProfileViewModel: MyProfileViewModel by viewModels{
        InjectorUtils.provideMyProfileViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentMyProfileBinding>(
            inflater, R.layout.fragment_my_profile, container, false
        ).apply{
            viewModel = myProfileViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        val adapter = MyProfileBadgeAdapter()

        binding.btnLogout.setOnClickListener{
            myProfileViewModel.deleteUser()
        }

        binding.rvBadge.adapter =adapter

        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: MyProfileBadgeAdapter) {
        myProfileViewModel.user.observe(viewLifecycleOwner) { result ->
            when(result){
                null -> adapter.submitList(null)
                else -> adapter.submitList(result.badges)
            }
        }
    }
}