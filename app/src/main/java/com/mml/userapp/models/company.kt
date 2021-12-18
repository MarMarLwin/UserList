package com.mml.userapp.models

import androidx.room.ColumnInfo
import java.io.Serializable

data class Company(
    var bs: String,
    var catchPhrase: String,
    @ColumnInfo(name="company_name")
    var name: String
):Serializable