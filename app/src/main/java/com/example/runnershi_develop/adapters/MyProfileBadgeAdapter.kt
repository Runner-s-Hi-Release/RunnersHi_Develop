package com.example.runnershi_develop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.runnershi_develop.R
import com.example.runnershi_develop.data.Badge
import com.example.runnershi_develop.databinding.ListItemBadgeBinding
import com.example.runnershi_develop.utilities.OnClickListener

class MyProfileBadgeAdapter(private val onClick: (badge: Badge?)-> Unit): ListAdapter<Boolean, BadgeViewHolder>(
    BadgeDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeViewHolder {
        return BadgeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_badge, parent, false
            ), onClick
        )
    }

    override fun onBindViewHolder(holder: BadgeViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}

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

private class BadgeDiffCallback : DiffUtil.ItemCallback<Boolean>() {

    override fun areItemsTheSame(
        oldItem: Boolean,
        newItem: Boolean
    ): Boolean {
        return false
    }

    override fun areContentsTheSame(
        oldItem: Boolean,
        newItem: Boolean
    ): Boolean {
        return false
    }
}