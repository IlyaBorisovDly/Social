package com.example.social.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: UsersApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersApi::class.java)
    }
}