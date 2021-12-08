package com.example.social.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.social.model.api.RetrofitInstance
import com.example.social.model.db.UsersDatabase
import com.example.social.model.entities.User
import com.example.social.model.repositories.Repository
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val api = RetrofitInstance.api
    private val userDao = UsersDatabase.getInstance(getApplication()).userDao()
    private val repository = Repository(api, userDao)

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    init {
        loadUsers()
    }

    fun getUserById(id: Int): User? {
        users.value?.forEach { user ->
            if (user.id == id) {
                return user
            }
        }

        return null
    }

    fun updateUsers() {
        viewModelScope.launch {
            repository.updateUsers()?.let {
                _users.value = it
            }
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _users.value = repository.getUsers()
        }
    }
}