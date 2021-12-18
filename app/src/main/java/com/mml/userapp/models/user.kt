package com.mml.userapp.models

import androidx.room.Embedded
import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "user",primaryKeys = ["id"])
data class User ( @Embedded
                  val company: Company,
                  @Embedded
                  val address: Address,
                  val email: String,
                  val id: Int,
                  val name: String,
                  val phone: String,
                  val username: String,
                  val website: String):Serializable
