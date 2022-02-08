package com.g19.projetopdm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.g19.projetopdm.data.user.User
import com.g19.projetopdm.data.user.UserViewModel

class SignUpActivity: AppCompatActivity() {

    lateinit var userViewModel: UserViewModel
    private lateinit var loginBackButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val signUpButton = findViewById<TextView>(R.id.textViewSignUp)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        signUpButton.setOnClickListener { createUser() }
        loginBackButton = findViewById(R.id.LoginText)
        loginBackButton.setOnClickListener { goToLogin() }
    }

    private fun createUser() {
        val errMsg = findViewById<TextView>(R.id.signupError)
        val name = findViewById<EditText>(R.id.nameInputSignUp).text.toString()
        val username = findViewById<EditText>(R.id.usernameInputSignUp).text.toString()
        val password = findViewById<EditText>(R.id.passwordInputSignUp).text.toString()
        val passwordCheck = findViewById<EditText>(R.id.confirmPasswordInputSignUp).text.toString()

        if (password == passwordCheck) {
            val user = User(0, name, username, password, 0.0f)
            userViewModel.addUser(user)
            Log.d("Signup", "New user registered.")
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            errMsg.text = "As passwords não coincidem"
            errMsg.isVisible = true
        }
    }

    private fun goToLogin() {
        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}