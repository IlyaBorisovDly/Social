package com.example.social.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: Int,
    val guid: String,
    val isActive: Boolean,
    val balance: String,
    val age: Int,
    val eyeColor: String,
    val name: String,
    val gender: String,
    val company: String,
    val email: String,
    val phone: String,
    val address: String,
    val about: String,
    val registered: String,
    val latitude: Double,
    val longitude: Double,
    val tags: List<String>,
    val friends: List<Friend>,
    val favoriteFruit: String
): Parcelable