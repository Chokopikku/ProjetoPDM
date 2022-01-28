package com.g19.projetopdm.data.vehicle

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVehicle(vehicle: Vehicle)

    @Query("SELECT * FROM Vehicle ORDER BY ID ASC")
    fun allVehicles(): LiveData<List<Vehicle>>

    @Query("DELETE FROM Vehicle where id =(:id)")
    fun removeById (id: Int) : Vehicle

    @Query("SELECT * FROM Vehicle where type =(:type)")
    fun findByType (type: String) : Vehicle


}