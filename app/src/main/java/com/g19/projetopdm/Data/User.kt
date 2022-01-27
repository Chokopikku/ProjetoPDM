package com.g19.projetopdm.Data
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true)
    val user_id: Int,
    val name: String,
    val username : String,
    val password : String,
    val wallet : Float

)

