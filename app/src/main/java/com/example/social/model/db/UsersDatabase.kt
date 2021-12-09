package com.example.social.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.social.model.entities.User

@Database(entities = [User::class], version = 1)
@TypeConverters(Converters::class)
abstract class UsersDatabase: RoomDatabase() {
    abstract fun userDao(): UsersDao

    companion object {
        private const val DATABASE_NAME = "database.db"

        private var instance: UsersDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): UsersDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): UsersDatabase {
            return Room.databaseBuilder(context, UsersDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
        }

    }
}