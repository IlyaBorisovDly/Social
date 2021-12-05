package com.example.social.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM USERS")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM USERS WHERE ID = :id")
    fun getUserById(id: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<User>)
}