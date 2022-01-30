package com.g19.projetopdm

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.g19.projetopdm.data.ProgramDatabase
import com.g19.projetopdm.data.user.User
import com.g19.projetopdm.data.user.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = findViewById<TextView>(R.id.loginbutton)
        val signup =findViewById<TextView>(R.id.signUpText)
        login.setOnClickListener { login() }
        signup.setOnClickListener { signup() }
    }

    private fun login() {
        val ref = this
        val username = findViewById<EditText>(R.id.usernameInput).text.toString()
        val password = findViewById<EditText>(R.id.passwordInput).text.toString()
        val errorMsg = findViewById<TextView>(R.id.errorText)
        lifecycleScope.launch(Dispatchers.IO) {
            val ProgramDatabase: ProgramDatabase = ProgramDatabase.getDatabase(applicationContext)
            val userDao: UserDao = ProgramDatabase.userDao()
            val user: User = userDao.login(username, password)

            ref.runOnUiThread {
                // if (user.getCount()) {
                if (user != null) {
                    val uid: Int = (user.id)
                    val uperm: Int = (user.permissionLevel)
                    val uname: String = (user.username)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    // intent.putExtra(user)
                    intent.putExtra("uid", uid)
                    intent.putExtra("uperm", uperm)
                    intent.putExtra("uname", uname)
                    startActivity(intent)
                    finish()
                } else {
                    errorMsg.text = "No such combination. Try again"
                    errorMsg.isVisible = true
                }
            }
        }
    }

    private fun signup() {
        val intent = Intent(this@LoginActivity, SignupActivity::class.java)
        startActivity(intent)
    }
}