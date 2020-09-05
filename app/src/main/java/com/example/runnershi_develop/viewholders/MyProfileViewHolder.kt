package com.example.runnershi_develop.viewholders

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.runnershi_develop.HomeViewPagerFragmentDirections
import com.example.runnershi_develop.databinding.ListItemBadgeBinding
import com.example.runnershi_develop.viewmodels.BadgeItemViewModel

class MyProfileViewHolder(private val binding: ListItemBadgeBinding) : RecyclerView.ViewHolder(binding.root) {

    init{
        binding.setClickListener { view ->
            binding.viewModel?.let { viewModel ->
                if(viewModel.badge)
                    navigateToPlant(viewModel.index, view)
            }
        }
    }

    fun bind(badgeBool: Boolean, index: Int) {
        with(binding) {
            viewModel= BadgeItemViewModel(badgeBool, index)
            executePendingBindings()
        }
    }

    private fun navigateToPlant(index: Int, view: View) {
        val direction = HomeViewPagerFragmentDirections
            .actionViewPagerFragmentToFragmentBadgeDetail(index)
        view.findNavController().navigate(direction)
    }
}