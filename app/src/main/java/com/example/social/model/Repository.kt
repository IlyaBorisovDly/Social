package com.example.social.model

import android.util.Log

const val TAG = "myTag"

class Repository(
    private val api: Api,
    private val userDao: UserDao
) {
    suspend fun getUsers(): List<User> {
        var users = userDao.getAllUsers()

        if (users.isEmpty()) {
            Log.d(TAG, "users are empty")
            val response = api.getUsers()

            if (response.isSuccessful && response.body() != null) {
                users = response.body()!!
                userDao.insert(users)
            }
        }

        return users
    }

    fun getUserById(id: Int): User {
        return userDao.getUserById(id)
    }
}