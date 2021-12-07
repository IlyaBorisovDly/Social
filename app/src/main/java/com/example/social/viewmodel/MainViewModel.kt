package com.example.social.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.social.model.api.RetrofitInstance
import com.example.social.model.db.UsersDatabase
import com.example.social.model.entities.User
import com.example.social.model.repositories.Repository
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

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

    private fun loadUsers() {
        val api = RetrofitInstance.api
        val userDao = UsersDatabase.getInstance(getApplication()).userDao()
        val repository = Repository(api, userDao)

        viewModelScope.launch {
            _users.value = repository.getUsers()
        }
    }
}