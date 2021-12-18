package com.mml.userapp.rest

import com.mml.userapp.models.User
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET("/users/")
    abstract fun getUser(): Call<List<User>>


}