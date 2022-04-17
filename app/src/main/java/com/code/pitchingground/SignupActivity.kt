package com.example.pg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.code.pitchingground.R
import com.example.pg.model.LoginInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity() {

    lateinit var nameSignup: EditText
    lateinit var usernameText: EditText
    lateinit var passwordText: EditText
    lateinit var confirmPasswordText: EditText
    lateinit var signUpBtn: Button
    lateinit var progressBar: ProgressBar
    lateinit var auth: FirebaseAuth
    lateinit var fstore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        nameSignup = findViewById(R.id.etName)
        usernameText = findViewById(R.id.etUsername)
        passwordText = findViewById(R.id.etPassword)
        confirmPasswordText = findViewById(R.id.etConfirmPassword)
        signUpBtn = findViewById(R.id.btnSignup)
        progressBar = findViewById(R.id.signUpProgressBar)
        auth = FirebaseAuth.getInstance()
        fstore = FirebaseFirestore.getInstance()


        initSignUp()
    }

    private fun initSignUp() {
        signUpBtn.setOnClickListener {
            val name = nameSignup.text.toString()
            val username = usernameText.text.toString()
            val password = passwordText.text.toString()
            val confirmPassword = confirmPasswordText.text.toString()
            when {
                !isPasswordValid(password) -> {
                    showToast("Password should be more than 8 characters")
                }
                !isEmailValid(username) -> {
                    showToast("Enter Valid Email")
                }
                !isPasswordMatch(password, confirmPassword) -> {
                    showToast("Password does not match the current password")
                }
                else -> {
                    auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener {
                        progressBar.visibility = View.VISIBLE
                        val userId = auth.currentUser!!.uid
                        val documentReference = fstore.collection("users").document(userId)
                        var user = HashMap<String, String>()
                        user.put("name", name)
                        documentReference.set(user).addOnCompleteListener {
                            if (it.isComplete && it.isSuccessful) {
                                showToast("User Created")
                                showMyselfActivity()
                            }
                            else {
                                progressBar.visibility = View.GONE
                                showToast(it.exception?.localizedMessage.toString())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showToast(text: String) {
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }


    private fun registerUser(username: String, password: String) {
        LoginInfo.users.add(Pair(username, password))
        progressBar.visibility = View.VISIBLE
        val timer = object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                progressBar.visibility = View.GONE
                showMyselfActivity()
                nameSignup.text.clear()
                usernameText.text.clear()
                passwordText.text.clear()
                confirmPasswordText.text.clear()
            }
        }.start()
    }

    fun showMyselfActivity() {
        val intent = Intent(this, MyselfActivity::class.java)
        startActivity(intent)
    }

    fun isPasswordValid(password: String) = password.length >= 8

    fun isEmailValid(email: String) = email.contains('@') &&
            email.isNotEmpty()

    fun isUsernameValid(username: String) = username.isNotEmpty()

    fun isPasswordMatch(password: String, confirmPassword: String) =
        password == confirmPassword


}