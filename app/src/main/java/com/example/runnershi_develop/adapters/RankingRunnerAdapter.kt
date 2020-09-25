package com.example.runnershi_develop.adapters

import android.service.notification.NotificationListenerService
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.runnershi_develop.data.RankingRunner
import com.example.runnershi_develop.databinding.ListItemRankingBinding

class RankingRunnerAdapter :
    ListAdapter<RankingRunner, RankingRunnerAdapter.ViewHolder>(RankingRunnerDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position + 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemRankingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RankingRunner, ranking: Int) {
            binding.apply {
                rankingRunner = item
                tvRankRank.text = ranking.toString()
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemRankingBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class RankingRunnerDiffCallback : DiffUtil.ItemCallback<RankingRunner>() {
    override fun areItemsTheSame(oldItem: RankingRunner, newItem: RankingRunner): Boolean {
        val oldItemUserIdx = when (oldItem) {
            is RankingRunner.WinOrLoseRunner -> oldItem.user_idx
            is RankingRunner.HeavyRunner -> oldItem.user_idx
        }
        val newItemUserIdx = when (newItem) {
            is RankingRunner.WinOrLoseRunner -> newItem.user_idx
            is RankingRunner.HeavyRunner -> newItem.user_idx
        }
        return oldItemUserIdx == newItemUserIdx
    }

    override fun areContentsTheSame(oldItem: RankingRunner, newItem: RankingRunner): Boolean {
        val oldItemContent = when (oldItem) {
            is RankingRunner.WinOrLoseRunner -> oldItem
            is RankingRunner.HeavyRunner -> oldItem
        }
        val newItemContent = when (newItem) {
            is RankingRunner.WinOrLoseRunner -> newItem
            is RankingRunner.HeavyRunner -> newItem
        }
        return oldItemContent == newItemContent
    }
}