package com.g19.projetopdm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SharePointRecyclerViewAdapter : RecyclerView.Adapter<SharePointRecyclerViewHolder>(){
    val sharePointList = arrayListOf("trotinete1", "carro1", "trotinete2", "carro2")


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SharePointRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_share_point_recycler_view_holder, parent, false)
        return SharePointRecyclerViewHolder(view)
    }
    override fun onBindViewHolder(holder: SharePointRecyclerViewHolder, position: Int) {
        holder.vehicleInfo.text = (position + 1).toString()
        holder.vehicleTitle.text = sharePointList[position]
    }

    override fun getItemCount(): Int {
        return sharePointList.size
    }
}