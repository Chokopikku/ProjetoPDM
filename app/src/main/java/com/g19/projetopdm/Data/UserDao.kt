package com.g19.projetopdm.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query



@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addViagem(viagem: Viagem)

    @Query("SELECT * FROM User ORDER BY username ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM Viagem ")
    fun readAllViagens(): LiveData<List<Viagem>>

    @Query("DELETE FROM Viagem")
    fun removeViagens()

    @Query("SELECT * FROM User where username =(:username) and password =(:password)")
    fun findByUsername (username: String, password : String) : User

    @Query("SELECT wallet FROM User where user_id =(:user_id)")
    fun findByUser_id (user_id : Int) : Float

    @Query("SELECT name FROM User where user_id =(:user_id)")
    fun findName (user_id : Int) : String

    @Query("UPDATE user SET wallet = (:currentMoney) WHERE user_id =(:user_id)")
    fun addMoney(user_id:Int, currentMoney : Float)

    @Query("UPDATE user SET wallet = (:currentMoney) WHERE user_id =(:user_id)")
    fun makePayment(user_id:Int, currentMoney : Float)

}