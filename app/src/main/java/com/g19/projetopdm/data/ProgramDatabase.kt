package com.g19.projetopdm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.g19.projetopdm.data.vehicle.Vehicle
import com.g19.projetopdm.data.vehicle.VehicleDao


@Database(entities = [Vehicle::class], version = 1, exportSchema = false)
abstract class ProgramDatabase: RoomDatabase() {

    abstract fun vehicleDao(): VehicleDao
    //quando tivermos o user, chamar aqui o DAO como em cima

    companion object{
        @Volatile
        private var INSTANCE: ProgramDatabase? = null

        fun getDatabase(context: Context): ProgramDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProgramDatabase::class.java,
                    "PDM-Mobilidade"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}