package com.example.social.ui.userdetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.social.model.User

class PagerAdapter(
    fragmentActivity: FragmentActivity,
    private val user: User
): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) UserInfoFragment(user) else UserFriendsFragment(user.friends)
    }

}