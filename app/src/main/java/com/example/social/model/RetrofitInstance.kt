package com.example.social.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}