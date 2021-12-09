package com.example.social.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.social.databinding.FragmentUserDetailsBinding
import com.example.social.model.entities.User
import com.example.social.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator

const val USER_ARGUMENT = "user"

class UserDetailsFragment: Fragment() {

    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        arguments?.getParcelable<User>(USER_ARGUMENT)?.let { user ->
            val friends = getUserFriends(user)
            val pagerAdapter = PagerAdapter(this@UserDetailsFragment, user, friends)

            binding.viewPager.apply {
                adapter = pagerAdapter
                getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
            }

            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                val tabNames = listOf("Информация", "Друзья")
                tab.text = tabNames[position]
            }.attach()
        }
    }

    override fun onStop() {
        super.onStop()
        binding.tabLayout.getTabAt(0)?.select()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getUserFriends(user: User): List<User> {
        val friends = mutableListOf<User>()

        user.friends.forEach {
            val friend = viewModel.getUserById(it.id)!!
            friends.add(friend)
        }

        return friends
    }

    companion object {

        @JvmStatic
        fun newInstance(user: User) =
            UserDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER_ARGUMENT, user)
                }
            }
    }
}