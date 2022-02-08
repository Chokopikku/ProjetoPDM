package com.g19.projetopdm.data.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM User WHERE username = (:username) AND password = (:password) LIMIT 1")
    fun login(username: String, password: String): User

    @Query("SELECT * FROM User WHERE id = (:id) LIMIT 1")
    fun getUser(id: Int): User

    @Query("SELECT balance FROM User WHERE id = (:id) LIMIT 1")
    fun getBalance(id: Int): Float

    @Query("UPDATE User SET balance = (:balance) WHERE id = (:id)")
    fun updateBalance(id: Int, balance: Float)
}