package com.example.trashtracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trashtracker.R
import com.example.trashtracker.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private var binding:ActivitySignUpBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.tvLoginPage?.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this,SignInActivity::class.java))
        finish()
    }
}