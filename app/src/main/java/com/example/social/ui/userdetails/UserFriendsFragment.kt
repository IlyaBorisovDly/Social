package com.example.social.ui.userdetails

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.social.databinding.FragmentUserFriendsBinding
import com.example.social.model.Friend
import com.example.social.model.User
import com.example.social.ui.CallbackListener
import com.example.social.ui.MainAdapter

class UserFriendsFragment(private val userFriends: List<Friend>) : Fragment() {

    private var _binding: FragmentUserFriendsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserFriendsBinding.inflate(inflater, container, false)

//        userFriends.forEach { getUserById }
//        setupRecycler()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecycler(users: List<User>) {
        val recyclerView = binding.recyclerViewUserFriends
        val callbackListener = getCallbackListener()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MainAdapter(users, callbackListener, )
    }

    private fun getCallbackListener(): CallbackListener {
        return object: CallbackListener {
            override fun onItemClicked(user: User) {
                val intent = Intent(context, UserDetailsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}