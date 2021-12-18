package com.mml.userapp.listeners
import com.mml.userapp.models.User

interface AdapterCallback {
    fun onMethodCallback(user: User)
}