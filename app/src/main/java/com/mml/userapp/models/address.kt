package com.mml.userapp.models

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Address(
    var city: String,
    var street: String,
    var suite: String,
    var zipcode: String,
    @Embedded
    @SerializedName("geo")
    var geo:Geo
): Serializable
