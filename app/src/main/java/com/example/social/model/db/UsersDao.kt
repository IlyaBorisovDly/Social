package com.example.social.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.social.model.entities.User

@Dao
interface UsersDao {

    @Query("SELECT * FROM USERS")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM USERS WHERE ID = :id")
    fun getUserById(id: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<User>)
}