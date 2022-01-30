package com.g19.projetopdm.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val permissionLevel: Int,
    val username: String,
    val password: String,
    val historyId: Int
)
