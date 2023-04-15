package com.example.trashtracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trashtracker.R
import com.example.trashtracker.databinding.ActivityMainBinding
import com.example.trashtracker.utils.GetLocation

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val currentLocation = GetLocation(this)

        binding?.btnMap?.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            val xLatitude = currentLocation.getCurrentLatLng().latitude
            val xLongitude = currentLocation.getCurrentLatLng().longitude
            intent.putExtra("latitude",xLatitude)
            intent.putExtra("longitude",xLongitude)
            startActivity(intent)
        }

        binding?.btnSettings?.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}