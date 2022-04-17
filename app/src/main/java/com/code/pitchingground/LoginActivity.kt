package com.example.pg

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.code.pitchingground.R
import com.example.pg.model.LoginInfo
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.sign

class LoginActivity : AppCompatActivity() {

    lateinit var signInButton: Button
    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var signUp: TextView
    lateinit var progressBar: ProgressBar
    lateinit var auth: FirebaseAuth


    companion object {
        const val INTENT_KEY = "key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        signInButton = findViewById(R.id.signInButton)
        usernameInput = findViewById(R.id.emailEditText)
        passwordInput = findViewById(R.id.passwordEditText)
        signUp = findViewById(R.id.signUpTextView)
        progressBar = findViewById(R.id.signInProgressBar)

        auth = FirebaseAuth.getInstance()

        signUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

//        if(auth.currentUser != null) {
//            showMainActivity()
//        }

        signInButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            when {
                !isEmailValid(username) -> {   //Custom functions to check email
                    showToast("Invalid Email")
                }
                !isPasswordValid(password) -> { //Custom functions to check password
                    showToast("Password Must be more than 8 characters")
                }
                else -> authenticateUser(username,password)
            }
        }
    }


    private fun isEmailValid(email: String) = email.contains('@') && email.isNotBlank()

    private fun isPasswordValid(password: String) = password.length >= 8

    private fun authenticateUser(username: String, password: String) {
        progressBar.visibility = View.VISIBLE
        auth.signInWithEmailAndPassword(username,password).addOnCompleteListener {
            if (it.isSuccessful && it.isComplete) {
                showMainActivity()
            }
            else {
                progressBar.visibility = View.GONE
                showToast(it.exception?.localizedMessage.toString())
            }
        }
    }

    private fun showToast(text: String) {
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    private fun showMainActivity() {
        val user = Intent(this, MyselfActivity::class.java)
        startActivity(user)
    }
}