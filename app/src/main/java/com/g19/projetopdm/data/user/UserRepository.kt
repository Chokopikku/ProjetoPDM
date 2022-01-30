package com.g19.projetopdm.data.user

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val getUsers: LiveData<List<User>> = userDao.getUsers()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun login(username: String, password: String){
        userDao.login(username, password)
    }
}