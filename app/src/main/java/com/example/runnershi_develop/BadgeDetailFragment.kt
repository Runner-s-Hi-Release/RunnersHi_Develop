package com.example.runnershi_develop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.runnershi_develop.databinding.FragmentBadgeDetailBinding
import com.example.runnershi_develop.utilities.InjectorUtils
import com.example.runnershi_develop.viewmodels.BadgeDetailViewModel

class BadgeDetailFragment : Fragment() {

    private val args: BadgeDetailFragmentArgs by navArgs()

    private val badgeDetailViewModel: BadgeDetailViewModel by viewModels{
        InjectorUtils.provideBadgeDetailViewModelFactory(args.index)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentBadgeDetailBinding>(
            inflater, R.layout.fragment_badge_detail, container, false
        ).apply{
            viewModel = badgeDetailViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        binding.setClickListenerBack{view ->
            view.findNavController().navigateUp()
        }

        return binding.root
    }

}