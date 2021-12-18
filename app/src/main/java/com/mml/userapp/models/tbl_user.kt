package com.mml.userapp.models

import androidx.room.Entity
import java.io.Serializable

@Entity(tableName="tblUser", primaryKeys = ["id"])
data class tblUser (
//        @SerializedName("address")
//        var address: String,
//        @SerializedName("company")
//        var company: String,
    var id: Int,
    var username: String,
    var name: String,
    var email: String,
    var phone: String,
    var website: String
): Serializable