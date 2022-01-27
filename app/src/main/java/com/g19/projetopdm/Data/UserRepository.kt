package com.g19.projetopdm.Data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()
    val readAllViagem: LiveData<List<Viagem>> = userDao.readAllViagens()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
    suspend fun addViagem(viagem: Viagem){
        userDao.addViagem(viagem)
    }
    suspend fun addMoney(user_id: Int, currentMoney : Float){
        userDao.addMoney(user_id,currentMoney)
    }

    suspend fun makePayment(user_id: Int, cuurentMoney : Float){
        userDao.addMoney(user_id,cuurentMoney)
    }

    suspend fun removeViagens(){
        userDao.removeViagens()
    }


}