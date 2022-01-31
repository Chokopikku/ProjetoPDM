package com.g19.projetopdm.lists

import android.content.Context
import android.database.DataSetObserver
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.g19.projetopdm.R
import com.g19.projetopdm.data.vehicle.Vehicle

class VehicleAdapter(val context: Context) : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>(),
    Adapter {
    val allVehicles = ArrayList<Vehicle>()


    inner class VehicleViewHolder(vehicleView : View) : RecyclerView.ViewHolder(vehicleView){
        var vehicleType : TextView = vehicleView.findViewById(R.id.vehicle_Item_Type)
        var vehicleModel : TextView = vehicleView.findViewById(R.id.vehicle_Item_Model)
        var vehicleMake : TextView = vehicleView.findViewById(R.id.vehicle_Item_Make)
        var vehicleBtn : Button = vehicleView.findViewById(R.id.vehicle_Rent)

    }
    override fun getItemCount(): Int {
        return allVehicles.size
    }
    fun updateList(newList : List<Vehicle>){

        Log.d("check", (newList.size).toString())
        allVehicles.addAll(newList)
        for(i in newList.size - 1 downTo 0){
            Log.d("teste10", newList[i].toString())
        }
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val viewHolder = VehicleViewHolder(LayoutInflater.from(context).inflate(R.layout.vehicle_item,parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val currentVechicle = allVehicles[position]
        holder.vehicleType.text = currentVechicle.type
        holder.vehicleMake.text = currentVechicle.make
        holder.vehicleModel.text = currentVechicle.model
    }

    override fun registerDataSetObserver(p0: DataSetObserver?) {

    }

    override fun unregisterDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

}