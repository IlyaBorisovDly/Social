package com.example.social.ui.mainscreen

import android.app.Application
import androidx.lifecycle.*
import com.example.social.model.*
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

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
            _users.value = repository.getUsers()
        }
    }
}