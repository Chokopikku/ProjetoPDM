package com.g19.projetopdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.g19.projetopdm.databinding.ActivityVehicleMapBinding
import com.google.android.gms.maps.model.Marker

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.g19.projetopdm.data.vehicle.Vehicle
import com.g19.projetopdm.data.vehicle.VehicleViewModel
import com.g19.projetopdm.lists.VehicleAdapter


class VehicleMap : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityVehicleMapBinding
    private lateinit var vehicleViewModel: VehicleViewModel
    private lateinit var recyclerView: RecyclerView
    private var adapter: VehicleAdapter? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vehicle0 = Vehicle(0,"Car","23cm3",3,"Y","Tesla",true)
        val vehicle1 = Vehicle(0,"Trotinete","23cm3",3,"Y","Tesla",true)
        val vehicle2 = Vehicle(0,"Bicicle","23cm3",3,"Y","Tesla",true)

        vehicleViewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
        vehicleViewModel.addVehicle(vehicle0)
        vehicleViewModel.addVehicle(vehicle1)
        vehicleViewModel.addVehicle(vehicle2)

        binding = ActivityVehicleMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        recyclerView = findViewById(R.id.vehicle_recyclerView)


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val center = LatLng(40.10630819103435, -7.822119146421931)
        val porto = LatLng(41.11130339905875, -8.655062483019536)
        val braga = LatLng(41.523883246182486, -8.417601521236854)
        mMap.addMarker(MarkerOptions().position(porto).title("SharePoint do Porto"))
        mMap.addMarker(MarkerOptions().position(braga).title("SharePoint de Braga"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center))
        mMap.setMinZoomPreference(7.5f)
        mMap.setMaxZoomPreference(15f)
        mMap.setOnMarkerClickListener(this)



        recyclerView()

    }
    override fun onMarkerClick(marker: Marker): Boolean {

        Toast.makeText(this, "clicked on " + marker.title, Toast.LENGTH_SHORT).show()

        var tag: Int

        if (marker.title == "SharePoint do Porto") tag = 1
        recyclerTag(1)


        return false
    }

    fun recyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = VehicleAdapter(this)
        recyclerView.adapter = adapter
        vehicleViewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
        vehicleViewModel.allVehicles.observe(this, Observer {  list ->
            list.let{ adapter?.updateList(it)}
        })

    }

    fun recyclerTag(tag: Int){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = VehicleAdapter(this)
        recyclerView.adapter = adapter
        vehicleViewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
        vehicleViewModel.findBySharepoint(tag).observe(this, Observer {  list ->
            list.let{ adapter?.updateList(it)}
        })
    }
}