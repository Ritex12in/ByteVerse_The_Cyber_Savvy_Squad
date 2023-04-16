package com.example.trashtrackerDemo.utils

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class ClaimsYAxisValueFormatter : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return if(value>=1000)
            (value/1000).toString()
        else
            value.toString()
    }
}