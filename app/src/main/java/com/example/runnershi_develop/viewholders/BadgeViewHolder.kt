package com.example.runnershi_develop.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.runnershi_develop.databinding.ListItemBadgeBinding
import com.example.runnershi_develop.data.Badge
import com.example.runnershi_develop.utilities.OnClickListener

class BadgeViewHolder(private val binding: ListItemBadgeBinding, private val onClick: (badge: Badge?)-> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(badgeBool: Boolean, index: Int) {
        with(binding) {
            badgeData = Badge(
                badgeBool,
                index
            )
            onClickListener = OnClickListener<Badge>(onClick)
            executePendingBindings()
        }
    }
}