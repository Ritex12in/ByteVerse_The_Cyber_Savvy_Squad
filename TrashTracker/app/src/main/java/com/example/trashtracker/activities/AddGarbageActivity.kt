package com.example.trashtracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trashtracker.R
import com.example.trashtracker.databinding.ActivityAddGarbageBinding
import com.example.trashtracker.firebase.FireStoreClass
import com.example.trashtracker.models.Garbage
import com.example.trashtracker.utils.GetLocation
import java.text.SimpleDateFormat
import java.util.*

class AddGarbageActivity : AppCompatActivity() {
    private var binding: ActivityAddGarbageBinding? = null
    private var selectedType:String = "generalWaste"
    private var selectedAmount:Double = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGarbageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.selectWasteType?.setOnCheckedChangeListener { _, i ->
            selectedType = if (i == R.id.generalWaste) {
                "generalWaste"
            } else {
                "plasticWaste"
            }
        }
        binding?.selectWasteAmount?.setOnCheckedChangeListener { _, i ->
            selectedAmount = when (i) {
                R.id.small -> 1.0
                R.id.medium -> 10.0
                else -> 100.0
            }
        }
        val currentPosition = GetLocation(this)

        binding?.markGarbageLocation?.setOnClickListener {
            val userId = FireStoreClass().getCurrentUserId()
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val currentDate = sdf.format(Date()).toString()
            val latitude = currentPosition.getCurrentLatLng().latitude
            val longitude = currentPosition.getCurrentLatLng().longitude
            val garbage= Garbage(userId,selectedType,currentDate,selectedAmount,latitude,longitude)
            FireStoreClass().addGarbage(this,garbage)
            FireStoreClass().addMonthData(selectedAmount)
            FireStoreClass().addYearData(selectedAmount)
            FireStoreClass().addWeekData(selectedAmount)
        }
    }
}