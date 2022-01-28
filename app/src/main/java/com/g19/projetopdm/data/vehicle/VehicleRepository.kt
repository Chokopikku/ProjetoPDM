package com.g19.projetopdm.data.vehicle

import androidx.lifecycle.LiveData

class VehicleRepository(private val vehicleDao: VehicleDao) {

    val allVehicles: LiveData<List<Vehicle>> = vehicleDao.allVehicles()
    val getAllTypes: LiveData<List<String>> = vehicleDao.getAllTypes()

    suspend fun addVehicle(vehicle: Vehicle){
        vehicleDao.addVehicle(vehicle)
    }

    suspend fun findByType(type: String){
        vehicleDao.findByType(type)
    }

    suspend fun removeVehicle(id: Int){
        vehicleDao.removeById(id)
    }


}