package com.example.social.ui

import com.example.social.model.User

interface CallbackListener {
    fun onItemClicked(user: User)
}