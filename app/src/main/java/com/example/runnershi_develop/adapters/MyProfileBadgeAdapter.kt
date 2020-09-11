package com.example.runnershi_develop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.runnershi_develop.R
import com.example.runnershi_develop.viewholders.BadgeViewHolder

class MyProfileBadgeAdapter: ListAdapter<Boolean, BadgeViewHolder>(
    BadgeDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeViewHolder {
        return BadgeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_badge, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BadgeViewHolder, position: Int) {
        holder.bind(getItem(position), position)
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