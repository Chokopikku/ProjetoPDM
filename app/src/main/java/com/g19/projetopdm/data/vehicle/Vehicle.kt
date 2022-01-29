package com.g19.projetopdm.data.vehicle

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Vehicle")
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val type: String,
    val consumption : String,
    val sharePoint: Int,
    val model: String,
    val make : String,
    val available: Boolean
)
