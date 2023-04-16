package com.example.trashtrackerDemo.utils

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*

class YearsXAxisValueFormatter(var datesList: List<String>) :
    ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {

        val position = value.toInt()
        val sdf = SimpleDateFormat("yyyy")

        return if (position < datesList.size)
            sdf.format(
                Date(
                    Utils.getDateInMilliSeconds(
                        datesList[position], "yyyy-MM-dd"
                    )
                )
            )
        else ""
    }
}