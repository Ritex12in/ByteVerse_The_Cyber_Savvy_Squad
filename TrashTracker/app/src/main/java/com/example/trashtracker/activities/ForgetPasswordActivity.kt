package com.example.trashtracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trashtracker.R

class ForgetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
    }

    override fun onBackPressed() {
        finish()
    }
}