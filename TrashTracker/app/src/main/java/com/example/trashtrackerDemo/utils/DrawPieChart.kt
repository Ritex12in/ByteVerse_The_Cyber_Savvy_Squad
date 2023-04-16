package com.example.trashtrackerDemo.utils

import android.graphics.Color
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter

object DrawPieChart {

    fun setupPieChart(pieChart: PieChart) {
        pieChart.isDrawHoleEnabled = true
        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelTextSize(12F)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.centerText = "Waste type distribution"
        pieChart.setCenterTextSize(24F)
        pieChart.description.isEnabled = false
        val l: Legend = pieChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = true
    }

    fun loadPieChartData(pieChart: PieChart,generalWaste:Float,plasticWaste:Float) {
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(generalWaste, "General Waste"))
        entries.add(PieEntry(plasticWaste, "Plastic Waste"))
        val colors= ArrayList<Int>()
        colors.add(Color.parseColor("#4A91F2"))
        colors.add(Color.parseColor("#964B00"))
        val dataSet = PieDataSet(entries, "Expense Category")
        dataSet.colors = colors
        val data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)
        pieChart.data = data
        pieChart.invalidate()
        pieChart.animateY(1400, Easing.EaseInOutQuad)
    }
}