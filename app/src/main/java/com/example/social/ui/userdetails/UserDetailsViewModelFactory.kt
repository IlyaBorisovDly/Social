package com.example.social.ui.userdetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.social.model.Friend

class UserDetailsViewModelFactory(
    private val application: Application,
    private val friends: List<Friend>
): ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserDetailsViewModel(application, friends) as T
    }
}