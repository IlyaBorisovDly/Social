package com.example.social.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.social.databinding.ActivityMainBinding
import com.example.social.model.entities.User
import com.example.social.ui.details.UserDetailsFragment
import com.example.social.ui.list.UsersFragment
import com.example.social.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), UsersFragment.CallbackListener {

    private val viewModel: MainViewModel by viewModels()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            launchUsersFragment()
        }
    }

    override fun onItemClick(user: User) {
        launchUserDetailsFragment(user)
    }

    private fun launchUserDetailsFragment(user: User) {
        if (user.isActive) {
            val userDetailsFragment = UserDetailsFragment.newInstance(user)
            val transaction = supportFragmentManager.beginTransaction()

            transaction
                .addToBackStack("Users fragment")
                .replace(binding.fragmentContainerView.id, userDetailsFragment)
                .commit()
        } else {
            Toast.makeText(this, "Пользователь не активен", Toast.LENGTH_SHORT).show()
        }
    }

    private fun launchUsersFragment() {
        viewModel.users.observe(this, { users ->
            val usersFragment = UsersFragment.newInstance(users)
            val transaction = supportFragmentManager.beginTransaction()

            transaction
                .replace(binding.fragmentContainerView.id, usersFragment)
                .commit()
        })
    }
}

