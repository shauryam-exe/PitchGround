package com.example.pg

import android.content.Intent
import android.widget.FrameLayout
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.MenuItem
import com.code.pitchingground.R
import com.example.pg.fragments.*

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomePageActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var addButton : FloatingActionButton
    private var selectorFragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)



        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
        bottomNavigationView = findViewById(R.id.bottomnavigationbar)
        addButton = findViewById(R.id.fab)

        bottomNavigationView.background = null

        bottomNavigationView.menu.getItem(2).isEnabled = true

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment())
            .commit()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.HomeNav -> {
                    selectorFragment = HomeFragment()
                }
                R.id.ChatNav -> {
                    selectorFragment = ChatFragment()
                }
                R.id.notificationNav -> {
                    selectorFragment = NotificationFragment()
                }
                R.id.profileNav -> {
                    selectorFragment = ProfileFragment()
                }
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectorFragment!!).commit()

            return@setOnNavigationItemSelectedListener true
        }

        addButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, UploadFragment()).commit()
        }
    }
}