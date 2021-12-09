package com.example.social.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
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

            with(binding) {
                textViewUserName.text = user.name
                textViewUserName.setDrawableEnd(indicator)
                textViewUserEmail.text = user.email

                cardViewUser.setOnClickListener {
                    listener.onItemClick(user)
                }
            }

        }

        private fun TextView.setDrawableEnd(source: Int) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, source, 0)
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