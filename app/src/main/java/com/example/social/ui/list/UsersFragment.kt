package com.example.social.ui.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.social.databinding.FragmentUsersBinding
import com.example.social.model.entities.User
import com.example.social.ui.USERS_ARGUMENT

class UsersFragment: Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener: CallbackListener

    interface CallbackListener {
        fun onItemClick(user: User)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = requireActivity() as CallbackListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)

        arguments?.takeIf { it.containsKey(USERS_ARGUMENT) }?.apply {
            val users = getParcelableArrayList<User>(USERS_ARGUMENT) as List<User>

            setupRecycler(users)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecycler(users: List<User>) {
        val recyclerView = binding.recyclerViewUsers

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = UsersAdapter(users, listener)
    }
}