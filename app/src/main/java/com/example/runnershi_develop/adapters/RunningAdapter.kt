package com.example.runnershi_develop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.runnershi_develop.data.Running
import com.example.runnershi_develop.databinding.ListItemRecordBinding
import com.example.runnershi_develop.utilities.OnClickListener

class RunningAdapter(private val onClickListener: OnClickListener<Running>) :
    ListAdapter<Running, RunningAdapter.ViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemRecordBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return ViewHolder(
            ListItemRecordBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            ), onClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(
        private var binding: ListItemRecordBinding,
        private val onClickListener: OnClickListener<Running>
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(running: Running) {
            binding.running = running
            binding.clickListener = onClickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Running>() {
        override fun areItemsTheSame(oldItem: Running, newItem: Running): Boolean {
            return oldItem.runIdx == newItem.runIdx
        }

        override fun areContentsTheSame(oldItem: Running, newItem: Running): Boolean {
            return (oldItem.runIdx == newItem.runIdx
                    && oldItem.gameIdx == newItem.gameIdx)
        }
    }
}
