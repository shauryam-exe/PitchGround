package com.code.pitchingground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.pg.HomePageActivity
import com.example.pg.LoginActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val timer = object: CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                showHomeActivity()
            }
        }
        timer.start()
    }

    private fun showHomeActivity() {
        val user = Intent(this, LoginActivity::class.java)
        startActivity(user)
    }
}