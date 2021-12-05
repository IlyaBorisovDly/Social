package com.example.social.ui.mainscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.social.model.RetrofitInstance
import com.example.social.model.User
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel: ViewModel() {

    private var users = MutableLiveData<List<User>>()

    init {
        loadUsers()
    }

    fun getUsers(): LiveData<List<User>> {
        return users
    }

    private fun loadUsers() {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.api.getUsers()
            } catch (e: IOException) {
                Log.d(TAG, "IOException")
                return@launch
            } catch (e: HttpException) {
                Log.d(TAG, "HttpException")
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                users.value = response.body()!!
            } else {
                Log.d(TAG, "Response is not successful")
            }
        }
    }
}