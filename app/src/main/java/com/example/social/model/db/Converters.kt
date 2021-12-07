package com.example.social.model.db

import androidx.room.TypeConverter
import com.example.social.model.entities.Friend

class Converters {

    companion object {

        @TypeConverter
        @JvmStatic
        fun fromStringList(value: List<String>): String {
            return value.joinToString(",")
        }

        @TypeConverter
        @JvmStatic
        fun toStringList(value: String): List<String> {
            return value.split(",")
        }

        @TypeConverter
        @JvmStatic
        fun fromFriendsList(value: List<Friend>): String {
            val sb = StringBuilder()

            for (i in 0 until value.size - 1) {
                sb.append(value[i].id)
                sb.append(",")
            }

            sb.append(value.last().id)

            return sb.toString()
        }

        @TypeConverter
        @JvmStatic
        fun toFriendsList(value: String): List<Friend> {
            val resultList = mutableListOf<Friend>()

            value.split(",").forEach {
                val friend = Friend(it.toInt())
                resultList.add(friend)
            }

            return resultList
        }
    }
}