package com.example.social.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.social.databinding.FragmentUsersBinding
import com.example.social.model.entities.User
import com.example.social.viewmodel.MainViewModel
import java.util.ArrayList

private const val USERS_ARGUMENT = "users"

class UsersFragment: Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val listener by lazy { requireActivity() as CallbackListener }

    interface CallbackListener {
        fun onItemClick(user: User)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        val users = arguments?.getParcelableArrayList<User>(USERS_ARGUMENT) as List<User>

        binding.swipeRefreshLayout.apply {
            setOnRefreshListener {
                viewModel.updateUsers()
                isRefreshing = false
            }
        }

        setupRecycler(users)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecycler(users: List<User>) {
        binding.recyclerViewUsers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = UsersAdapter(users, listener)
        }
    }
    
    companion object {
        fun newInstance(users: List<User>) =
            UsersFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(USERS_ARGUMENT, users as ArrayList<User>)
                }
            }
    }

}