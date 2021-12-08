package com.example.social.model.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

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
): Parcelable {

    fun getFormattedAge(): String {
        return if (age % 100 in 11..14) {
            "$age лет"
        } else if (age % 10 == 1) {
            "$age год"
        } else if (age % 10 == 0 || age % 10 in 5..9) {
            "$age лет"
        } else {
            "$age года"
        }
    }

    fun getCoordinates(): String {
        return "$latitude, $longitude"
    }

    fun getFormattedRegistrationDate(): String {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val outputFormatter = SimpleDateFormat("HH:mm dd.MM.yy", Locale.US)
        val date = inputFormatter.parse(registered)!!

        return outputFormatter.format(date)
    }

    fun getEyeColorSource(): Int {
        EyeColor.values().forEach {
            if (eyeColor == it.title) {
                return it.source
            }
        }

        return EyeColor.Green.source
    }

    fun getFavouriteFruitSource(): Int {
        FavouriteFruit.values().forEach {
            if (favoriteFruit == it.title) {
                return it.source
            }
        }

        return FavouriteFruit.Apple.source
    }
}