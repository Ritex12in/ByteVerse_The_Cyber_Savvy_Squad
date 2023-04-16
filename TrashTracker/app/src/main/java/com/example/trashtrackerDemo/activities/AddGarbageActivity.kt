package com.example.trashtrackerDemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trashtrackerDemo.R
import com.example.trashtrackerDemo.databinding.ActivityAddGarbageBinding
import com.example.trashtrackerDemo.firebase.FireStoreClass
import com.example.trashtrackerDemo.models.Garbage
import com.example.trashtrackerDemo.utils.GetLocation
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