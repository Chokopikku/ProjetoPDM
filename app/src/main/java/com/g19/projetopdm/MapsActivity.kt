package com.g19.projetopdm

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.g19.projetopdm.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource
import java.util.ArrayList

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    companion object {
        private const val LOCATION_REQUEST_CODE = 1
    }

    // Declaration to handle recycler view
    lateinit var sharePointRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // get client location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        // get recycler view id
        /*sharePointRecyclerView = findViewById(R.id.recyclerView)

        sharePointRecyclerView.layoutManager = LinearLayoutManager(this)

        sharePointRecyclerView.adapter = SharePointRecyclerViewAdapter()*/

    }



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // marker clicklistener
        mMap.setOnMarkerClickListener { marker ->
            if (marker.isInfoWindowShown) {
                marker.hideInfoWindow()
            } else {
                marker.showInfoWindow()
            }
            true
        }

        // Add a marker in Porto Portugal and move the camera
        val porto = LatLng(41.15, -8.61)
        mMap.addMarker(MarkerOptions().position(porto).title("Marker in Porto"))

        var porto2 = LatLng(41.16, -8.62)
        mMap.addMarker(MarkerOptions().position(porto2).title("Marker in Porto2"))

        var porto3 = LatLng(41.16, -8.60)
        mMap.addMarker(MarkerOptions().position(porto3).title("Marker in Porto3"))

        mMap.moveCamera(CameraUpdateFactory.newLatLng(porto))
        mMap.setOnMapClickListener{this}

        //mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style))
        fetchLocation()
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_REQUEST_CODE
            )
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationProviderClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                //mMap.addMarker(MarkerOptions().position(currentLatLong).title("Marker in Current Location"))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 16f))
            }
        }
    }


}