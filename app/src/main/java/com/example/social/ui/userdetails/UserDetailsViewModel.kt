package com.example.social.ui.userdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.social.model.*
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    application: Application,
    private val friends: List<Friend>
): AndroidViewModel(application) {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    init {
        loadUsers()
    }

    private fun loadUsers() {
        val api = RetrofitInstance.api
        val userDao = AppDatabase.getInstance(getApplication()).userDao()
        val repository = Repository(api, userDao)

        viewModelScope.launch {
            val usersList = mutableListOf<User>()

            friends.forEach {
                val user = repository.getUserById(it.id)
                usersList.add(user)
            }

            _users.value = usersList
        }
    }
}