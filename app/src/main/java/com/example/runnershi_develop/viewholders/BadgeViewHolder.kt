package com.example.runnershi_develop.viewholders

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.runnershi_develop.HomeViewPagerFragmentDirections
import com.example.runnershi_develop.databinding.ListItemBadgeBinding
import com.example.runnershi_develop.data.Badge

class BadgeViewHolder(private val binding: ListItemBadgeBinding) : RecyclerView.ViewHolder(binding.root) {

    init{
        binding.setClickListener { view ->
            binding.badgeData?.let { badgeData ->
                if(badgeData.badge)
                    navigateToBadgeDetail(badgeData.index, view)
            }
        }
    }

    fun bind(badgeBool: Boolean, index: Int) {
        with(binding) {
            badgeData= Badge(
                badgeBool,
                index
            )
            executePendingBindings()
        }
    }

    private fun navigateToBadgeDetail(index: Int, view: View) {
        val direction = HomeViewPagerFragmentDirections
            .actionViewPagerFragmentToFragmentBadgeDetail(index)
        view.findNavController().navigate(direction)
    }
}