package com.g19.projetopdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.g19.projetopdm.data.vehicle.Vehicle
import com.g19.projetopdm.data.vehicle.VehicleViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var vehicleViewModel: VehicleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vehicleViewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
        vehicleViewModel.removeVehicle(0)
        //teste
        startActivity(Intent(this,VehicleMap::class.java))
        finish()


    }
}