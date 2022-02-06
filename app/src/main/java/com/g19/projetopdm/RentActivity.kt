package com.g19.projetopdm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.g19.projetopdm.data.ProgramDatabase
import com.g19.projetopdm.data.vehicle.Vehicle
import com.g19.projetopdm.data.vehicle.VehicleDao
import com.g19.projetopdm.data.vehicle.VehicleViewModel
import com.g19.projetopdm.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class RentActivity : AppCompatActivity() {

    private lateinit var playBtn : ImageView
    private lateinit var pauseBtn :ImageView
    private lateinit var chronomter:Chronometer
    var isPlay = false
    var pauseOffSet :Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rent)
        vehicleInfo()
        configure()
    }

    fun vehicleInfo(){
        var vehicleId = intent.getStringExtra("vehicleID").toString().toInt()

        val ref = this

        // add values to text view's
        val vehicleIDTextView = findViewById<TextView>(R.id.idValuetextView)
        val consumoTextView = findViewById<TextView>(R.id.consumoValueTextView)
        val precoTextView = findViewById<TextView>(R.id.priceValueTextView)
        var vehicleid= intent.getStringExtra("vehicleID").toString()



        lifecycleScope.launch (Dispatchers.IO){

            val ProgramDatabase : ProgramDatabase = ProgramDatabase.getDatabase(applicationContext)
            val vehicleDao: VehicleDao = ProgramDatabase.vehicleDao()

            val vehicle =  vehicleDao.findById(vehicleid.toInt())

            ref.runOnUiThread{
                if (vehicle != null){
                    vehicleIDTextView.text = vehicle.id.toString()
                    consumoTextView.text = vehicle.consumption
                }
            }
    }
    }

    fun configure(){


        playBtn = findViewById(R.id.playBtn)
        pauseBtn = findViewById(R.id.pauseBtn)
        chronomter = findViewById(R.id.chronoMterPlay)
        playBtn.setOnClickListener {
            /**set play*/
            if (!isPlay){
                chronomter.base = SystemClock.elapsedRealtime() - pauseOffSet
                chronomter.start()
                pauseBtn.visibility = View.GONE
                playBtn.setImageResource(R.drawable.ic_stop)
                textMsg("Aluguer iniciado !!",this)
                isPlay  =true

            }
            else{
                chronomter.base = SystemClock.elapsedRealtime()
                pauseOffSet = 0
                chronomter.stop()
                playBtn.setImageResource(R.drawable.ic_play)
                pauseBtn.visibility = View.GONE
                textMsg("Aluguer terminado !!",this)
                isPlay = false
            }

        }
        /*pauseBtn.setOnClickListener {
            if (isPlay){
                chronomter.stop()
                pauseOffSet = SystemClock.elapsedRealtime() - chronomter.base
                isPlay = false
                pauseBtn.setImageResource(R.drawable.ic_play)
                textMsg("Chronomter is Pause !!",this)
            }
            else{
                chronomter.base = SystemClock.elapsedRealtime() - pauseOffSet
                chronomter.start()
                pauseBtn.setImageResource(R.drawable.ic_pause)
                textMsg("Chronomter is Play !!",this)
                isPlay = true
            }
        }*/


    }
    fun textMsg(s:String,c:Context){
        Toast.makeText(c,s, Toast.LENGTH_SHORT).show()
    }
}
