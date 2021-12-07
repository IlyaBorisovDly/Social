package com.example.social.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.social.model.entities.User
import com.example.social.ui.USERS_ARGUMENT
import com.example.social.ui.list.UsersFragment

class PagerAdapter(
    fragment: Fragment,
    private val user: User,
    private val friends: List<User>
): FragmentStateAdapter(fragment) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) UserInfoFragment(user) else getUsersFragment()
    }

    private fun getUsersFragment(): UsersFragment {
        val usersFragment = UsersFragment()
        val bundle = Bundle()

        bundle.putParcelableArrayList(USERS_ARGUMENT, friends as ArrayList<User>)
        usersFragment.arguments = bundle

        return usersFragment
    }
}