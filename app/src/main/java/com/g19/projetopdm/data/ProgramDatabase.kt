package com.g19.projetopdm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.g19.projetopdm.data.user.User
import com.g19.projetopdm.data.user.UserDao
import com.g19.projetopdm.data.vehicle.Vehicle
import com.g19.projetopdm.data.vehicle.VehicleDao


@Database(version = 1, exportSchema = false, entities = [Vehicle::class, User::class])
abstract class ProgramDatabase: RoomDatabase() {

    abstract fun vehicleDao(): VehicleDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: ProgramDatabase? = null

        fun getDatabase(context: Context): ProgramDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this) {
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