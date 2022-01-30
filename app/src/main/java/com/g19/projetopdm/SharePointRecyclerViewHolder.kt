package com.g19.projetopdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SharePointRecyclerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    // get share point image

    val vehicleTitle = itemView.findViewById<TextView>(R.id.txtViewVehicleTitle)
    val vehicleInfo = itemView.findViewById<TextView>(R.id.txtViewVehicleInfo)
}