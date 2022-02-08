package com.g19.projetopdm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.g19.projetopdm.data.ProgramDatabase
import com.g19.projetopdm.data.vehicle.VehicleDao
import com.g19.projetopdm.databinding.ActivityVehicleMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehicleMapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private val matosinhos = LatLng(41.16783511208824, -8.687091618191157)
    private val baguimMonte = LatLng(41.18739973749522, -8.540007736199222)
    private val ermesinde = LatLng(41.215129046031926, -8.553404008671329)
    private val gaia = LatLng(41.12976818209694, -8.606419931811327)
    private val center = LatLng(40.10630819103435, -7.822119146421931)
    private val porto = LatLng(41.11130339905875, -8.655062483019536)
    private val braga = LatLng(41.523883246182486, -8.417601521236854)
    private val maia = LatLng(41.229512728605044, -8.62394518225489)
    private val vilaDoConde = LatLng(41.3522380764589, -8.74058506928767)
    private val famalicao = LatLng(41.40610782621928, -8.52074750159189)
    private val lisbon = LatLng(38.729907369824694, -9.132886962175466)
    private val cascais = LatLng(38.695443934584524, -9.424629431638879)
    private val setubal = LatLng(38.53053981830144, -8.896806563180334)
    private val portimao = LatLng(37.14641434328816, -8.5487224607713)
    private val sagres = LatLng(37.021522087697996, -8.974442670878442)
    private val faro = LatLng(37.02810049341605, -7.949967584620608)
    private val vilaReal = LatLng(41.337801875886626, -7.731845128026691)
    private val viseu = LatLng(40.67972376703032, -7.9376191840278)

    val cars = arrayOf<LatLng>(matosinhos,ermesinde,baguimMonte,gaia,porto,setubal,portimao,vilaDoConde,vilaReal,lisbon,maia,braga,famalicao)
    val trotinete = arrayOf<LatLng>(porto,matosinhos,setubal,portimao,vilaDoConde,vilaReal,lisbon,maia,braga)
    val bicicleta = arrayOf<LatLng>(setubal,lisbon,cascais,maia,ermesinde,viseu,matosinhos)
    val mota = arrayOf<LatLng>(matosinhos,ermesinde,maia,vilaReal,vilaDoConde,lisbon,famalicao,cascais,sagres)

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityVehicleMapBinding
    private var uid: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        binding = ActivityVehicleMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            uid = extras.getInt("uid")
        }

        configure()
    }
    private fun configure() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        val qrButton = findViewById<FloatingActionButton>(R.id.fabQr)

        val rentButton = findViewById<Button>(R.id.rentButton)
        val vehicleID = findViewById<EditText>(R.id.vehicleID)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        var typeOfVehicle = findViewById<Spinner>(R.id.type_of_vehicle_spinner)

        spinner(typeOfVehicle)

        qrButton.setOnClickListener {
            startActivity(Intent(this, QrScannerActivity::class.java))
        }

        rentButton.setOnClickListener {
            val ref = this
            val id = vehicleID.text.toString() //val id = vehicleID.text

            if (id.isEmpty() || id.equals(" ")){ // if id equals to empty string or space -> block
                Toast.makeText(applicationContext, "Insira o ID do veiculo", Toast.LENGTH_SHORT).show()
            }else{
                lifecycleScope.launch (Dispatchers.IO){

                    val ProgramDatabase : ProgramDatabase = ProgramDatabase.getDatabase(applicationContext)
                    val vehicleDao: VehicleDao = ProgramDatabase.vehicleDao()
                    val vehicle = vehicleDao.findById(id.toInt())

                    ref.runOnUiThread{
                        if (vehicle != null){
                            val intent = Intent(this@VehicleMapActivity, RentActivity::class.java)
                            intent.putExtra("vehicleId", id)
                            intent.putExtra("uid", uid)
                            startActivity(intent)
                            finish()
                        } else {
                            findViewById<TextView>(R.id.errorMsg).text = "Este ID não foi encontrado"
                            findViewById<TextView>(R.id.errorMsg).isVisible = true
                        }
                    }
                }


            }
        }
    }

    private fun spinner(spinner : Spinner){
        val option = arrayOf("Carro","Bicicleta","Trotinete","Mota")
        var selectedItem = ""


        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,option)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                selectedItem = spinner.selectedItem.toString()
                Toast.makeText(applicationContext, selectedItem + "s disponiveis", Toast.LENGTH_SHORT).show()
                if (selectedItem == "Carro"){
                    map.clear()
                    cars.forEach {
                        map.addMarker(MarkerOptions().position(it).title("Ponto de Partilha com Carros").icon(
                            fromResource(R.drawable.iconcar)
                        ))
                    }
                }
                if (selectedItem == "Trotinete"){
                    map.clear()
                    trotinete.forEach {
                        map.addMarker(MarkerOptions().position(it).title("SharePoint de").icon(
                            fromResource(R.drawable.icontrotinete1)
                        ))
                    }
                }
                if (selectedItem == "Bicicleta"){
                    map.clear()

                    bicicleta.forEach {
                        map.addMarker(MarkerOptions().position(it).title("SharePoint de").icon(
                            fromResource(R.drawable.bikeicon)))
                    }

                }
                if (selectedItem == "Mota"){
                    map.clear()

                    mota.forEach {
                        map.addMarker(MarkerOptions().position(it).title("SharePoint de").icon(
                            BitmapDescriptorFactory.fromResource(R.drawable.motorcycleicon)))
                    }
                }
            }
        }

        val extras = intent.extras
        if (extras != null) {
            spinner.setSelection(extras.getInt("filter"))
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.addMarker(MarkerOptions().position(matosinhos).title("SharePoint de Matosinhos"))
        map.addMarker(MarkerOptions().position(baguimMonte).title("SharePoint do Baguim do Monte"))
        map.addMarker(MarkerOptions().position(ermesinde).title("SharePoint de Ermesinde"))
        map.addMarker(MarkerOptions().position(gaia).title("SharePoint de Gaia"))
        map.addMarker(MarkerOptions().position(porto).title("SharePoint de Porto"))
        map.addMarker(MarkerOptions().position(braga).title("SharePoint de Braga"))
        map.addMarker(MarkerOptions().position(maia).title("SharePoint de Maia"))
        map.addMarker(MarkerOptions().position(vilaDoConde).title("SharePoint de Vila do Conde"))
        map.addMarker(MarkerOptions().position(famalicao).title("SharePoint de Famalicao"))
        map.addMarker(MarkerOptions().position(lisbon).title("SharePoint de Lisboa"))
        map.addMarker(MarkerOptions().position(cascais).title("SharePoint de Cascais"))
        map.addMarker(MarkerOptions().position(setubal).title("SharePoint de Setubal"))
        map.addMarker(MarkerOptions().position(portimao).title("SharePoint de Portimao"))
        map.addMarker(MarkerOptions().position(sagres).title("SharePoint de Sagres"))
        map.addMarker(MarkerOptions().position(faro).title("SharePoint de Faro"))
        map.addMarker(MarkerOptions().position(vilaReal).title("SharePoint de Vila Real"))
        map.addMarker(MarkerOptions().position(viseu).title("SharePoint de Viseu"))

        map.moveCamera(CameraUpdateFactory.newLatLng(center))
        map.setMinZoomPreference(7.5f)
        map.setMaxZoomPreference(15f)
        map.setOnMarkerClickListener(this)
    }
    override fun onMarkerClick(marker: Marker): Boolean {
        Toast.makeText(this, "Clicked on " + marker.title, Toast.LENGTH_SHORT).show()
        val tag: Int

        if (marker.title == "SharePoint do Porto") tag = 1

        return false
    }

  }