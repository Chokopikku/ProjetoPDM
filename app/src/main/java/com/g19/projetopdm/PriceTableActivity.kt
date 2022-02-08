package com.g19.projetopdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PriceTableActivity : AppCompatActivity() {
    private var uid: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price_table)

        val extras = intent.extras
        if (extras != null) {
            uid = extras.getInt("uid")
        }

        val homeButton = findViewById<TextView>(R.id.btnMain)
        homeButton.setOnClickListener {
            val intent = Intent(this@PriceTableActivity, MainActivity::class.java)
            intent.putExtra("uid", uid)
            startActivity(intent)
            finish()
        }
    }
}