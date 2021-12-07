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
import java.util.ArrayList

const val USER_ARGUMENT = "user"
const val USERS_ARGUMENT = "usersList"

class MainActivity : AppCompatActivity(), UsersFragment.CallbackListener {

    private val viewModel: MainViewModel by viewModels()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.users.observe(this, { users ->
            val transaction = supportFragmentManager.beginTransaction()
            val usersFragment = UsersFragment()
            val bundle = Bundle()

            bundle.putParcelableArrayList(USERS_ARGUMENT, users as ArrayList<User>)
            usersFragment.arguments = bundle

            transaction
                .replace(binding.fragmentContainerView.id, usersFragment)
                .commit()
        })
    }

    override fun onItemClick(user: User) {
        openUserDetailsFragment(user)
    }

    private fun openUserDetailsFragment(user: User) {
        val transaction = supportFragmentManager.beginTransaction()
        val userDetailsFragment = UserDetailsFragment()
        val bundle = Bundle()

        bundle.putParcelable(USER_ARGUMENT, user)
        userDetailsFragment.arguments = bundle

        if (user.isActive) {
            transaction
                .addToBackStack("Users fragment")
                .replace(binding.fragmentContainerView.id, userDetailsFragment)
                .commit()
        } else {
            Toast.makeText(this, "Пользователь не активен", Toast.LENGTH_SHORT).show()
        }
    }
}

