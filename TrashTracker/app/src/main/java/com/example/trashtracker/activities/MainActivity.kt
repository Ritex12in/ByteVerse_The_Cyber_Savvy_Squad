package com.example.trashtracker.activities

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trashtracker.R
import com.example.trashtracker.contants.Constants
import com.example.trashtracker.databinding.ActivityMainBinding
import com.example.trashtracker.firebase.FireStoreClass
import com.example.trashtracker.models.User
import com.example.trashtracker.utils.*
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setName()
        setProfilePic()
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

        createPieChart()

        val volumeReportChart: LineChart = findViewById(R.id.reportingChart)
        val volumeReportChart2: LineChart = findViewById(R.id.reportingChart2)
        val volumeReportChart3: LineChart = findViewById(R.id.reportingChart3)
        val volumeReportChart4: LineChart = findViewById(R.id.reportingChart4)
        val volumeReportChart5: LineChart = findViewById(R.id.reportingChart5)
        val volumeReportChart6: LineChart = findViewById(R.id.reportingChart6)
        val volumeReportChart7: LineChart = findViewById(R.id.reportingChart7)
        val volumeReportChart8: LineChart = findViewById(R.id.reportingChart8)
        val volumeReportChart9: LineChart = findViewById(R.id.reportingChart9)
        val dates = listOf(
            "2023-04-12", "2023-04-13", "2023-04-14", "2023-04-15",
            "2023-04-16", "2023-04-17", "2023-04-18"
        )
        val monthDates = listOf("2023-04-12", "2023-05-13", "2023-06-14", "2023-07-15",
            "2023-08-16", "2023-09-17", "2023-10-18")

        val yearDates = listOf("2023-04-12", "2024-04-13", "2025-04-14", "2026-04-15",
            "2027-04-16", "2028-04-17", "2029-04-18")

        val allAmounts = listOf(44.0, 33.0, 22.0, 200.0, 23.0, 75.0, 45.0)
        SetGraph.renderData(WeeksXAxisValueFormatter(dates),volumeReportChart, allAmounts,8,"Week",true,"#FF0000")
        SetGraph.renderData(MonthsXAxisValueFormatter(monthDates),volumeReportChart2, allAmounts,8,"Month",false,"#00FF00")
        SetGraph.renderData(YearsXAxisValueFormatter(yearDates),volumeReportChart3, allAmounts,8,"Year",false,"#0000FF")
        SetGraph.renderData(WeeksXAxisValueFormatter(dates),volumeReportChart4, allAmounts,8,"Week",true,"#000000")
        SetGraph.renderData(MonthsXAxisValueFormatter(monthDates),volumeReportChart5, allAmounts,8,"Month",false,"#80FF01")
        SetGraph.renderData(YearsXAxisValueFormatter(yearDates),volumeReportChart6, allAmounts,8,"Year",false,"#0500FF")
        SetGraph.renderData(WeeksXAxisValueFormatter(dates),volumeReportChart7, allAmounts,8,"Week",true,"#FF0A00")
        SetGraph.renderData(MonthsXAxisValueFormatter(monthDates),volumeReportChart8, allAmounts,8,"Month",false,"#D0FF90")
        SetGraph.renderData(YearsXAxisValueFormatter(yearDates),volumeReportChart9, allAmounts,8,"Year",false,"#E060FF")
    }

    private fun setName()
    {
        val mFireStore = FirebaseFirestore.getInstance()
        mFireStore.collection(Constants.USERS).document(FireStoreClass().getCurrentUserId()).get().addOnSuccessListener { document->
            val user = document.toObject(User::class.java)
            if (user != null) {
                binding?.tvMainName?.text = user.name
                binding?.tvMainContribution?.text = "Total Contributions: ${user.contribution}"
            }
        }
    }

    private fun createPieChart()
    {
        val dataRefer = FirebaseFirestore.getInstance().collection(Constants.CONSTANTS).document(Constants.WASTETYPEDATA)
        dataRefer.get().addOnSuccessListener {document->
            //val generalWaste = document.get("generalWaste").toString().toFloat()
            //val plasticWaste = document.get("plasticWaste").toString().toFloat()
            val pieChart: PieChart = findViewById(R.id.activity_main_pieChart)
            DrawPieChart.setupPieChart(pieChart)
            DrawPieChart.loadPieChartData(pieChart, 9f,4f)
        }
    }

    private fun setProfilePic()
    {
        val storageRef = Firebase.storage.reference
        val pathReference = storageRef.child("profilephoto/profilepic1.png")
        val ONE_MEGABYTE: Long = 1024 * 1024
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener {byteArray->
            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

            binding?.mainProfilePic?.setImageBitmap(bmp)
        }
    }
}