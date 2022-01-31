package com.g19.projetopdm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.g19.projetopdm.databinding.ActivityVehicleMapBinding
import androidx.lifecycle.ViewModelProvider
import com.g19.projetopdm.data.vehicle.Vehicle
import com.g19.projetopdm.data.vehicle.VehicleViewModel
import com.g19.projetopdm.lists.VehicleAdapter
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource


class VehicleMap : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityVehicleMapBinding
    private lateinit var vehicleViewModel: VehicleViewModel
    //private lateinit var recyclerView: RecyclerView
    private var adapter: VehicleAdapter? = null

    val matosinhos = LatLng(41.16783511208824, -8.687091618191157)
    val baguimMonte = LatLng(41.18739973749522, -8.540007736199222)
    val ermesinde = LatLng(41.215129046031926, -8.553404008671329)
    val gaia = LatLng(41.12976818209694, -8.606419931811327)
    val center = LatLng(40.10630819103435, -7.822119146421931)
    val porto = LatLng(41.11130339905875, -8.655062483019536)
    val braga = LatLng(41.523883246182486, -8.417601521236854)
    val maia = LatLng(41.229512728605044, -8.62394518225489)
    val vilaDoConde = LatLng(41.3522380764589, -8.74058506928767)
    val famalicao = LatLng(41.40610782621928, -8.52074750159189)
    val lisbon = LatLng(38.729907369824694, -9.132886962175466)
    val cascais = LatLng(38.695443934584524, -9.424629431638879)
    val setubal = LatLng(38.53053981830144, -8.896806563180334)
    val portimao = LatLng(37.14641434328816, -8.5487224607713)
    val sagres = LatLng(37.021522087697996, -8.974442670878442)
    val faro = LatLng(37.02810049341605, -7.949967584620608)
    val vilaReal = LatLng(41.337801875886626, -7.731845128026691)
    val viseu = LatLng(40.67972376703032, -7.9376191840278)

    val cars = arrayOf<LatLng>(matosinhos,ermesinde,baguimMonte,gaia,porto,setubal,portimao,vilaDoConde,vilaReal,lisbon,maia,braga,famalicao)
    val trotinete = arrayOf<LatLng>(porto,matosinhos,setubal,portimao,vilaDoConde,vilaReal,lisbon,maia,braga)
    val bicicleta = arrayOf<LatLng>(setubal,lisbon,cascais,maia,ermesinde,viseu,matosinhos)
    val mota = arrayOf<LatLng>(matosinhos,ermesinde,maia,vilaReal,vilaDoConde,lisbon,famalicao,cascais,sagres)


    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        getSupportActionBar()?.hide()
        binding = ActivityVehicleMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configure()
    }


    fun configure() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        var typeOfVehicle = findViewById<Spinner>(R.id.type_of_vehicle_spinner)

        spinner(typeOfVehicle)
    }

    fun addData(){
        val vehicle0 = Vehicle(0,"Car","23cm3",3,"Y","Tesla",true)
        val vehicle1 = Vehicle(0,"Trotinete","23cm3",3,"Y","Tesla",true)
        val vehicle2 = Vehicle(0,"Bicicle","23cm3",3,"Y","Tesla",true)

        vehicleViewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
        vehicleViewModel.addVehicle(vehicle0)
        vehicleViewModel.addVehicle(vehicle1)
        vehicleViewModel.addVehicle(vehicle2)
    }

    fun spinner(spinner : Spinner){

        val option = arrayOf("Carro","Bicicleta","Trotinete","Mota")

        var selectedItem : String = ""


        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,option)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                selectedItem = spinner.selectedItem.toString()

                Toast.makeText(applicationContext, selectedItem + "s disponiveis", Toast.LENGTH_SHORT).show()

                if (selectedItem == "Carro"){
                    map.clear()

                    cars.forEach {
                        map.addMarker(MarkerOptions().position(it).title("Ponto de Partilha com Carros"))
                    }
                }
                if (selectedItem == "Trotinete"){
                    map.clear()
                    trotinete.forEach {
                        map.addMarker(MarkerOptions().position(it).title("SharePoint de"))
                    }
                }
                if (selectedItem == "Bicicleta"){
                    map.clear()

                    bicicleta.forEach {
                        map.addMarker(MarkerOptions().position(it).title("SharePoint de"))
                    }

                }
                if (selectedItem == "Mota"){
                    map.clear()

                    mota.forEach {
                        map.addMarker(MarkerOptions().position(it).title("SharePoint de"))
                    }

                }

            }
        }
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
        map = googleMap


        map.addMarker(MarkerOptions().position(matosinhos).title("SharePoint de Matosinhos"))
        map.addMarker(MarkerOptions().position(baguimMonte).title("SharePoint do Baguim do Monte"))
        map.addMarker(MarkerOptions().position(ermesinde).title("SharePoint de Ermesinde"))
        map.addMarker(MarkerOptions().position(gaia).title("SharePoint de gaia"))
        map.addMarker(MarkerOptions().position(porto).title("SharePoint de porto"))
        map.addMarker(MarkerOptions().position(braga).title("SharePoint de braga"))
        map.addMarker(MarkerOptions().position(maia).title("SharePoint de maia"))
        map.addMarker(MarkerOptions().position(vilaDoConde).title("SharePoint de vilaDoConde"))
        map.addMarker(MarkerOptions().position(famalicao).title("SharePoint de famalicao"))
        map.addMarker(MarkerOptions().position(lisbon).title("SharePoint de lisbon"))
        map.addMarker(MarkerOptions().position(cascais).title("SharePoint de Cascais"))
        map.addMarker(MarkerOptions().position(setubal).title("SharePoint de setubal"))
        map.addMarker(MarkerOptions().position(portimao).title("SharePoint de Portimao"))
        map.addMarker(MarkerOptions().position(sagres).title("SharePoint de Sagres"))
        map.addMarker(MarkerOptions().position(faro).title("SharePoint de faro"))
        map.addMarker(MarkerOptions().position(vilaReal).title("SharePoint de vilaReal"))
        map.addMarker(MarkerOptions().position(viseu).title("SharePoint de Viseu"))

        map.moveCamera(CameraUpdateFactory.newLatLng(center))
        map.setMinZoomPreference(7.5f)
        map.setMaxZoomPreference(15f)
        map.setOnMarkerClickListener(this)
    }
    override fun onMarkerClick(marker: Marker): Boolean {

        Toast.makeText(this, "clicked on " + marker.title, Toast.LENGTH_SHORT).show()

        var tag: Int

        if (marker.title == "SharePoint do Porto") tag = 1
        //recyclerTag(1)


        return false
    }




}
