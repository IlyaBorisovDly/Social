package com.example.social.model.repositories

import android.util.Log
import com.example.social.model.api.UsersApi
import com.example.social.model.db.UsersDao
import com.example.social.model.entities.User

const val TAG = "myTag"

class Repository(
    private val usersApi: UsersApi,
    private val usersDao: UsersDao
) {
    suspend fun getUsers(): List<User> {
        var users = usersDao.getAllUsers()

        if (users.isEmpty()) {
            Log.d(TAG, "users are empty")
            val response = usersApi.getUsers()

            if (response.isSuccessful && response.body() != null) {
                users = response.body()!!
                usersDao.insert(users)
            }
        }

        return users
    }

    fun getUserById(id: Int): User {
        return usersDao.getUserById(id)
    }
}