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
    private val getUsers: LiveData<List<User>>

    init {
        val userDao = ProgramDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getUsers = repository.getUsers
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun login(username: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(username, password)
        }
    }
}