package com.example.social.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.social.R
import com.example.social.databinding.ItemUserBinding
import com.example.social.model.entities.User

class UsersAdapter(
    private val users: List<User>,
    private val listener: UsersFragment.CallbackListener
): RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UserViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]

        holder.bind(user)
    }

    override fun getItemCount() = users.size

    inner class UserViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            val indicator = getIndicatorByActive(user.isActive)

            binding.textViewUserName.text = user.name
            binding.textViewUserName
                .setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, indicator, 0)

            binding.textViewUserEmail.text = user.email

            binding.cardViewUser.setOnClickListener {
                listener.onItemClick(user)
            }
        }

        private fun getIndicatorByActive(isActive: Boolean): Int {
            return if (isActive) {
                R.drawable.ic_indicator_green
            } else {
                R.drawable.ic_indicator_red
            }
        }
    }
}