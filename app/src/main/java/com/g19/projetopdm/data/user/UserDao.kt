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

    @Query("SELECT * FROM User ORDER BY id ASC")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE username = (:username) AND password = (:password) LIMIT 1")
    fun login(username: String, password: String): User
}