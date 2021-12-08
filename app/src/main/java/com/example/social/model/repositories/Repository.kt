package com.example.social.model.repositories

import android.util.Log
import com.example.social.model.api.UsersApi
import com.example.social.model.db.UsersDao
import com.example.social.model.entities.User

class Repository(
    private val usersApi: UsersApi,
    private val usersDao: UsersDao
) {
    suspend fun getUsers(): List<User> {
        var users = usersDao.getAllUsers()

        if (users.isEmpty()) {
            val response = usersApi.getUsers()

            if (response.isSuccessful && response.body() != null) {
                users = response.body()!!
                usersDao.insert(users)
            }
        }

        return users
    }

    suspend fun updateUsers(): List<User>? {
        val response = usersApi.getUsers()

        if (response.isSuccessful && response.body() != null) {
            val users = response.body()!!
            usersDao.insert(users)

            return users
        }

        return null
    }
}