package com.example.trashtrackerDemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.trashtrackerDemo.R
import com.example.trashtrackerDemo.databinding.ActivitySettingsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingsActivity : AppCompatActivity() {
    private var binding: ActivitySettingsBinding? = null

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = Firebase.auth
        val btnSignOut: CardView = findViewById(R.id.cvSignOut)
        btnSignOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this,GetStartedActivity::class.java))
            finish()
        }

        binding?.btnDashboard?.setOnClickListener {
            finish()
        }
        binding?.btnMap?.setOnClickListener {
            startActivity(Intent(this,MapActivity::class.java))
            finish()
        }

        binding?.cvManageProfile?.setOnClickListener {
            startActivity(Intent(this,ManageProfileActivity::class.java))
        }
        binding?.ivBackBtn?.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}