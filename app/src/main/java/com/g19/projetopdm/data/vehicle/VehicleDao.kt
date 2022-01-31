package com.g19.projetopdm.data.vehicle

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVehicle(vehicle: Vehicle)

    @Query("SELECT * FROM Vehicle ORDER BY ID ASC")
    fun allVehicles(): LiveData<List<Vehicle>>

    @Query("DELETE FROM Vehicle where id =(:id)")
    fun removeById (id: Int)

    @Query("SELECT * FROM Vehicle where type =(:type)")
    fun findByType (type: String) : LiveData<List<Vehicle>>

    @Query("SELECT DISTINCT type FROM vehicle")
    fun getAllTypes(): LiveData<List<String>>

    @Query("SELECT * FROM Vehicle where sharePoint =(:sharePoint)")
    fun findBySharepoint (sharePoint: Int) : LiveData<List<Vehicle>>

}
