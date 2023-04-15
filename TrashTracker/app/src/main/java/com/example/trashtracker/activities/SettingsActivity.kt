package com.example.trashtracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trashtracker.R
import com.example.trashtracker.databinding.ActivitySettingsBinding
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity() {
    private var binding: ActivitySettingsBinding? = null

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnDashboard?.setOnClickListener {
            finish()
        }
        binding?.btnMap?.setOnClickListener {
            startActivity(Intent(this,MapActivity::class.java))
            finish()
        }
    }
}