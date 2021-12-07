package com.example.social.model.api

import com.example.social.model.entities.User
import retrofit2.Response
import retrofit2.http.GET

interface UsersApi {

    @GET("/v0/b/candidates--questionnaire.appspot.com/o/users.json?alt=media&token=e3672c23-b1a5-4ca7-bb77-b6580d75810c")
    suspend fun getUsers(): Response<List<User>>
}