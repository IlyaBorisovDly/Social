package com.example.social.ui.details

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.social.model.entities.User
import com.example.social.ui.list.UsersFragment

class PagerAdapter(
    fragment: Fragment,
    private val user: User,
    private val friends: List<User>
): FragmentStateAdapter(fragment) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            UserInfoFragment.newInstance(user)
        } else {
            UsersFragment.newInstance(friends)
        }
    }
}