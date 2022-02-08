package com.g19.projetopdm.data.user

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    fun login(username: String, password: String) {
        userDao.login(username, password)
    }

    fun getUser(id: Int) {
        userDao.getUser(id)
    }

    fun getBalance(id: Int) {
        userDao.getBalance(id)
    }

    fun updateBalance(id: Int, balance: Float) {
        userDao.updateBalance(id, balance)
    }
}