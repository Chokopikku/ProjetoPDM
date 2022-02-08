package com.g19.projetopdm

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.g19.projetopdm.data.ProgramDatabase
import com.g19.projetopdm.data.vehicle.VehicleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RentActivity : AppCompatActivity() {

    private lateinit var playBtn: ImageView
    private lateinit var finishBtn: Button
    private lateinit var backButton: Button
    private lateinit var chronometer: Chronometer
    private var uid: Int = 0
    var isPlay = false
    var pauseOffSet :Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rent)

        val extras = intent.extras
        if (extras != null) {
            uid = extras.getInt("uid")
        }

        vehicleInfo()
        configure()
        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            intent.putExtra("uid", uid)
            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    fun vehicleInfo(){
        val ref = this
        // add values to text view's
        val vehicleTextView = findViewById<TextView>(R.id.VehicleTextView)
        val consumoTextView = findViewById<TextView>(R.id.consumoValueTextView)
        val precoTextView = findViewById<TextView>(R.id.priceValueTextView)
        val vehicleId = intent.getStringExtra("vehicleId").toString()

        lifecycleScope.launch (Dispatchers.IO){
            val ProgramDatabase : ProgramDatabase = ProgramDatabase.getDatabase(applicationContext)
            val vehicleDao: VehicleDao = ProgramDatabase.vehicleDao()

            val vehicle =  vehicleDao.findById(vehicleId.toInt())

            ref.runOnUiThread{
                vehicleTextView.text = "${vehicle.make} ${vehicle.model}"
                consumoTextView.text = vehicle.consumption
                when (vehicle.type) {
                    "Carro" -> precoTextView.text = getString(R.string.car_price)
                    "Trotinete" -> precoTextView.text = getString(R.string.scooter_price)
                    "Mota" -> precoTextView.text = getString(R.string.bike_price)
                    "bicicleta" -> precoTextView.text = getString(R.string.bicycle_price)
                    else -> "N/A"
                }
            }
    }
    }

    fun configure(){
        chronometer = findViewById(R.id.chronometerPlay)
        playBtn = findViewById(R.id.playBtn)
        playBtn.setOnClickListener {
            /* Set play */
            if (!isPlay) {
                chronometer.base = SystemClock.elapsedRealtime() - pauseOffSet
                chronometer.start()
                playBtn.setImageResource(R.drawable.ic_stop)
                textMsg("Aluguer iniciado!",this)
                isPlay = true
            } else {
                chronometer.base = SystemClock.elapsedRealtime()
                pauseOffSet = 0
                chronometer.stop()
                playBtn.setImageResource(R.drawable.ic_play)
                textMsg("Aluguer terminado!",this)
                isPlay = false
            }
        }

        finishBtn = findViewById(R.id.finishButton)
        finishBtn.setOnClickListener {
            /* Set play */
            if (isPlay) {
                chronometer.base = SystemClock.elapsedRealtime()
                pauseOffSet = 0
                chronometer.stop()
                playBtn.setImageResource(R.drawable.ic_play)
                textMsg("Aluguer terminado!",this)
                isPlay = false
            }
        }
    }

    fun textMsg(s:String,c:Context){
        Toast.makeText(c,s, Toast.LENGTH_SHORT).show()
    }
}
