package com.example.pg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.code.pitchingground.R
import com.example.pg.model.User
import com.google.firebase.auth.FirebaseAuth

class MyselfActivity : AppCompatActivity() {

    lateinit var founderButton: Button
    lateinit var investorButton: Button
    lateinit var exploreButton: Button
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myself)
        auth = FirebaseAuth.getInstance()
        val userID = auth.currentUser?.uid

        founderButton = findViewById(R.id.btnFounder)
        investorButton = findViewById(R.id.btnInvestor)
        exploreButton = findViewById(R.id.btnExplore)

        exploreButton.setOnClickListener {
            showHomeActivity()
        }

        investorButton.setOnClickListener {
            showKYIDialog()
        }

        founderButton.setOnClickListener {
            showGSTDialog()
        }
    }

    private fun showGSTDialog() {
        val myDialog = AlertDialog.Builder(this)
        val title = "We will take the GST number of the Founder.\n" +
                "It is optional to enter GST details.\n" +
                "Founders who entered GST number will be verified."

        myDialog.setMessage(title)

        myDialog.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            showHomeActivity()
        }
        myDialog.create().show()
    }

    private fun showKYIDialog() {
        val myDialog = AlertDialog.Builder(this)
        val title = "We will Verify Investor Details from Know Your Investor API"
        myDialog.setTitle(title)

        myDialog.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            showHomeActivity()
        }
        myDialog.create().show()
    }

    private fun showHomeActivity() {
        val user = Intent(this, HomePageActivity::class.java)
        startActivity(user)
    }
}