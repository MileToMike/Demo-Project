package com.zonvoir.demoproject.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zonvoir.demoproject.databinding.ListItemUserBinding
import com.zonvoir.demoproject.model.User
import com.zonvoir.demoproject.ui.UserDetailsActivity

class UserAdapter(private val onClick: (User) -> Unit) :
    ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        Log.d("thisisTheLog", "bind: $user")
        holder.bind(user)
    }

    inner class UserViewHolder(private val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
//            binding.root.setOnClickListener { onClick(user) }
            binding.root.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, UserDetailsActivity::class.java).apply {
                    putExtra(UserDetailsActivity.EXTRA_USER_ID, user.id)
                }
                context.startActivity(intent)
            }
            binding.executePendingBindings()

        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}
