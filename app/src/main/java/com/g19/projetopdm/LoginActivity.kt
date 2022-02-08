package com.g19.projetopdm

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.g19.projetopdm.data.ProgramDatabase
import com.g19.projetopdm.data.user.User
import com.g19.projetopdm.data.user.UserDao
import com.g19.projetopdm.data.user.UserViewModel
import com.g19.projetopdm.data.vehicle.Vehicle
import com.g19.projetopdm.data.vehicle.VehicleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity: AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initUsers()

        val login = findViewById<TextView>(R.id.loginbutton)
        val signup = findViewById<TextView>(R.id.signUpText)
        login.setOnClickListener { login() }
        signup.setOnClickListener { signup() }
    }

    private fun initUsers() {
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.addUser(User(1, "Fabio", "fabio", "1234", 0.0f))
        userViewModel.addUser(User(2, "Carlos", "carlos", "abcd", 0.0f))
        userViewModel.addUser(User(3, "Avito", "avito", "12ab", 0.0f))
    }

    private fun login() {
        val ref = this
        val username = findViewById<EditText>(R.id.usernameInput).text.toString()
        val password = findViewById<EditText>(R.id.passwordInput).text.toString()
        val errorMsg = findViewById<TextView>(R.id.errorText)
        lifecycleScope.launch(Dispatchers.IO) {
            val programDatabase: ProgramDatabase = ProgramDatabase.getDatabase(applicationContext)
            val userDao: UserDao = programDatabase.userDao()
            val user: User = userDao.login(username, password)

            ref.runOnUiThread {
                if (user != null) {
                    val uid: Int = (user.id)
                    val uname: String = (user.username)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("uid", uid)
                    intent.putExtra("uname", uname)
                    startActivity(intent)
                    finish()
                } else {
                    errorMsg.text = "Credenciais erradas..."
                    errorMsg.isVisible = true
                }
            }
        }
    }

    private fun signup() {
        val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
        startActivity(intent)
    }
}