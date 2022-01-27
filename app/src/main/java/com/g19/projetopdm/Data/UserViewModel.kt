package com.g19.projetopdm.Data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    val readAllViagem: LiveData<List<Viagem>>
    val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
        readAllViagem = repository.readAllViagem
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun addViagem(viagem: Viagem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addViagem(viagem)
        }
    }
    fun addMoney(user_id: Int, currentMoney : Float){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMoney(user_id,currentMoney)
        }
    }
    fun makePayment(user_id: Int, currentMoney : Float){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMoney(user_id,currentMoney)
        }
    }
    fun removeViagens(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeViagens()
        }
    }
}
