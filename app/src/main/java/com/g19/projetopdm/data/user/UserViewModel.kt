package com.g19.projetopdm.data.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.g19.projetopdm.data.ProgramDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository

    init {
        val userDao = ProgramDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(username, password)
        }
    }

    fun getUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUser(id)
        }
    }

    fun getBalance(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBalance(id)
        }
    }

    fun updateBalance(id: Int, balance: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBalance(id, balance)
        }
    }
}