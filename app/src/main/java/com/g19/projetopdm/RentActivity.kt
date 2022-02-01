package com.g19.projetopdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rent)

        configurar()
    }

    fun configurar(){
        var qrCodeNumber: String = intent.getStringExtra("num").toString()
        findViewById<TextView>(R.id.idValuetextView).text = "Id do veículo: " + qrCodeNumber
    }

}