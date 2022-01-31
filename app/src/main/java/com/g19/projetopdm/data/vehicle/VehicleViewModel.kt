package com.g19.projetopdm.data.vehicle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.g19.projetopdm.data.ProgramDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehicleViewModel(application: Application): AndroidViewModel(application) {

    public val allVehicles: LiveData<List<Vehicle>>
    public val getAllTypes: LiveData<List<String>>
    public val repository: VehicleRepository

    init {
        val vehicleDao = ProgramDatabase.getDatabase(application).vehicleDao()
        repository = VehicleRepository(vehicleDao)
        allVehicles = repository.allVehicles
        getAllTypes = repository.getAllTypes
    }

    fun addVehicle(vehicle: Vehicle){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addVehicle(vehicle)
        }
    }
    fun findByType(type: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.findByType(type)
        }
    }
    fun removeVehicle(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeVehicle(id)
        }
    }

    fun findBySharepoint(sharePoint : Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.findBySharepoint(sharePoint)
        }
    }



}