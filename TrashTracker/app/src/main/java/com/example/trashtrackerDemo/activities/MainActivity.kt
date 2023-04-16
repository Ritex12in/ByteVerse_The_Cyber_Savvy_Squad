package com.example.trashtrackerDemo.activities

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.trashtrackerDemo.R
import com.example.trashtrackerDemo.contants.Constants
import com.example.trashtrackerDemo.databinding.ActivityMainBinding
import com.example.trashtrackerDemo.firebase.FireStoreClass
import com.example.trashtrackerDemo.models.User
import com.example.trashtrackerDemo.utils.*
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var mFireStore:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setName()
        setProfilePic()
        mFireStore= FirebaseFirestore.getInstance()
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
        binding?.fbAddGarbage?.setOnClickListener {
            startActivity(Intent(this,AddGarbageActivity::class.java))
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

//        val currentDay = SimpleDateFormat("dd-MM").format(Date()).toInt()
//        val currentMonth = SimpleDateFormat("MM-yy").format(Date()).toInt()
//        val currentYear = SimpleDateFormat("yyyy").format(Date()).toInt()
//        val dates = listOf( "2023-04-${currentDay-6}","2023-04-${currentDay-5}","2023-04-${currentDay-4}",
//            "2023-04-${currentDay-3}","2023-04-${currentDay-2}","2023-04-${currentDay-1}","2023-04-${currentDay}"
//        )
//        val monthDates = listOf("2023-${currentMonth-3}-12", "2023-${currentMonth-2}-13", "2023-${currentMonth-1}-14", "2023-${currentMonth}-15",
//            "2023-${currentMonth+1}-16", "2023-${currentMonth+2}-17", "2023-${currentMonth+3}-18")
//
//        val yearDates = listOf("2029-04-${currentYear-1}","2023-04-${currentYear}", "2024-04-${currentYear+1}", "2025-04-${currentYear+2}", "2026-04-${currentYear+3}",
//            "2027-04-${currentYear+4}", "2028-04-${currentYear+5}")
        val dates = listOf(
            "2023-04-12", "2023-04-13", "2023-04-14", "2023-04-15",
            "2023-04-16", "2023-04-17", "2023-04-18"
        )
        val monthDates = listOf("2023-04-12", "2023-05-13", "2023-06-14", "2023-07-15",
            "2023-08-16", "2023-09-17", "2023-10-18")

        val yearDates = listOf("2023-04-12", "2024-04-13", "2025-04-14", "2026-04-15",
            "2027-04-16", "2028-04-17", "2029-04-18")

        val allAmountsWeek1 = listOf(44.0, 33.0, 22.0, 200.0, 23.0, 75.0, 45.0)
        val allAmountsWeek2 = listOf(144.0, 133.0, 122.0, 200.0, 123.0, 175.0, 145.0)
        val allAmountsWeek3 = listOf(1441.0, 1331.01, 1221.0, 200.0, 123.0, 175.0, 145.0)
        val allAmountsMonth1 = listOf(1441.0, 1931.01, 1221.0, 208.0, 123.0, 475.0, 145.0)
        val allAmountsMonth2 = listOf(1441.0, 18131.01, 1221.0, 2018.0, 1213.0, 1475.0, 1145.0)
        val allAmountsMonth3 = listOf(14341.0, 181331.01, 1221.0, 2018.0, 12133.0, 14375.0, 1145.0)
        val allAmountsYear1 = listOf(13141.0, 18131.01, 12213.0, 20148.0, 121343.0, 1435.0, 1145.0)
        val allAmountsYear2 = listOf(13141.0, 18131.01, 132213.0, 20148.0, 1231343.0, 14305.0, 11345.0)
        val allAmountsYear3 = listOf(13141.0, 18131.01, 132213.0, 20148.0, 1231343.0, 145305.0, 113545.0)

        SetGraph.renderData(WeeksXAxisValueFormatter(dates),volumeReportChart, allAmountsWeek1,8,"Week",true,"#FF0000")
        SetGraph.renderData(MonthsXAxisValueFormatter(monthDates),volumeReportChart2, allAmountsMonth1,8,"Month",false,"#00FF00")
        SetGraph.renderData(YearsXAxisValueFormatter(yearDates),volumeReportChart3, allAmountsYear1,8,"Year",false,"#0000FF")
        SetGraph.renderData(WeeksXAxisValueFormatter(dates),volumeReportChart4, allAmountsWeek2,8,"Week",true,"#000000")
        SetGraph.renderData(MonthsXAxisValueFormatter(monthDates),volumeReportChart5, allAmountsMonth2,8,"Month",false,"#80FF01")
        SetGraph.renderData(YearsXAxisValueFormatter(yearDates),volumeReportChart6, allAmountsYear2,8,"Year",false,"#0500FF")
        SetGraph.renderData(WeeksXAxisValueFormatter(dates),volumeReportChart7, allAmountsWeek3,8,"Week",true,"#FF0A00")
        SetGraph.renderData(MonthsXAxisValueFormatter(monthDates),volumeReportChart8, allAmountsMonth3,8,"Month",false,"#D0FF90")
        SetGraph.renderData(YearsXAxisValueFormatter(yearDates),volumeReportChart9, allAmountsYear3,8,"Year",false,"#E060FF")
    }

    private fun setName()
    {
        val mFireStore = FirebaseFirestore.getInstance()
        mFireStore.collection(Constants.USERS).document(FireStoreClass().getCurrentUserId()).get().addOnSuccessListener { document->
            val user = document.toObject(User::class.java)
            if (user != null) {
                binding?.tvMainName?.text = user.name
                binding?.tvMainContribution?.text = "Total Contributions: ${user.contribution}"
                binding?.tvWeekContribtion?.text = user.contribution.toString()
            }
        }
    }

    private fun createPieChart()
    {
        val dataRefer = FirebaseFirestore.getInstance().collection(Constants.CONSTANTS).document(Constants.WASTETYPEDATA)
        dataRefer.get().addOnSuccessListener {document->
            val generalWaste = document.get("generalWaste").toString().toFloat()
            val plasticWaste = document.get("plasticWaste").toString().toFloat()
            val pieChart: PieChart = findViewById(R.id.activity_main_pieChart)
            DrawPieChart.setupPieChart(pieChart)
            DrawPieChart.loadPieChartData(pieChart, generalWaste,plasticWaste)
        }
    }

    private fun setProfilePic()
    {
        val mFireStore = FirebaseFirestore.getInstance()
        mFireStore.collection(Constants.USERS).document(FireStoreClass().getCurrentUserId()).get().addOnSuccessListener { document->
            val image = document.get("image").toString()
            val storageRef = Firebase.storage.reference
            val pathReference = storageRef.child(image)
            val ONE_MEGABYTE: Long = 1024 * 1024
            pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener {byteArray->
                val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

                binding?.mainProfilePic?.setImageBitmap(bmp)
            }
        }
    }
}