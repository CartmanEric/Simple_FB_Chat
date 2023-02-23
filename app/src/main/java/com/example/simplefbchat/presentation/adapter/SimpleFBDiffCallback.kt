package com.example.simplefbchat.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.simplefbchat.domain.model.Users

class SimpleFBDiffCallback : DiffUtil.ItemCallback<Users>() {
    override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
        return oldItem.message == newItem.message
    }

    override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
        return oldItem == newItem
    }
}