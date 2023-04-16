package com.example.trashtrackerDemo.utils

import android.graphics.Color
import android.graphics.DashPathEffect
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils

object SetGraph {

    fun renderData(
        xLabelFormat:ValueFormatter,
        volumeReportChart: LineChart,
        allAmounts: List<Double>,
        xLabelCount:Int,
        title:String,
        isCircle:Boolean,lineColor:String
    ) {

        volumeReportChart.setTouchEnabled(true)
        volumeReportChart.setPinchZoom(true)
        val xAxisLabel: ArrayList<String> = ArrayList()
        xAxisLabel.add("0")
        xAxisLabel.add("1")
        xAxisLabel.add("2")
        xAxisLabel.add("3")
        xAxisLabel.add("4")
        xAxisLabel.add("5")
        xAxisLabel.add("6")
        val xAxis = volumeReportChart.xAxis
        val position = XAxis.XAxisPosition.BOTTOM
        xAxis.position = position
        xAxis.enableGridDashedLine(2f, 7f, 0f)
        //xAxis.axisMaximum = 7f
        //xAxis.axisMinimum = 0f
        xAxis.setLabelCount(xLabelCount, true)
        xAxis.isGranularityEnabled = true
        xAxis.granularity = 7f
        xAxis.labelRotationAngle = 315f
        xAxis.valueFormatter = xLabelFormat
        xAxis.setCenterAxisLabels(true)
        xAxis.setDrawLimitLinesBehindData(true)

        val leftAxis = volumeReportChart.axisLeft
        leftAxis.removeAllLimitLines()

        leftAxis.enableGridDashedLine(10f, 10f, 0f)
        leftAxis.setDrawZeroLine(false)
        leftAxis.setDrawLimitLinesBehindData(false)
        //XAxis xAxis = mBarChart.getXAxis();
        leftAxis.valueFormatter = ClaimsYAxisValueFormatter()
        volumeReportChart.description.isEnabled = true
        val description = Description()

        description.text = title
        description.textSize = 15f
        volumeReportChart.description.setPosition(0f, 0f)
        volumeReportChart.description = description
        volumeReportChart.axisRight.isEnabled = false

        setDataForWeeksWise(volumeReportChart, allAmounts,isCircle,lineColor)
    }

    private fun setDataForWeeksWise(volumeReportChart: LineChart, amounts: List<Double>,isCircle:Boolean,lineColor:String) {
        val values: ArrayList<Entry> = ArrayList()
        for (i in amounts.indices) {
            values.add(Entry((i + 1).toFloat(), amounts[i].toFloat()))
        }

        val set1: LineDataSet
        if (volumeReportChart.data != null &&
            volumeReportChart.data.dataSetCount > 0
        ) {
            set1 = volumeReportChart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            volumeReportChart.data.notifyDataChanged()
            volumeReportChart.notifyDataSetChanged()
        } else {
            set1 = LineDataSet(values, "Total volume")
            set1.setDrawCircles(isCircle)
            set1.enableDashedLine(10f, 0f, 0f)
            set1.enableDashedHighlightLine(10f, 0f, 0f)
            set1.color = Color.parseColor(lineColor)
            set1.setCircleColor(Color.parseColor(lineColor))
            set1.lineWidth = 2f //line size
            set1.circleRadius = 5f
            set1.setDrawCircleHole(isCircle)
            set1.valueTextSize = 10f
            set1.setDrawFilled(true)
            set1.formLineWidth = 5f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 5f
            if (Utils.getSDKInt() >= 18) {
                set1.fillColor = Color.WHITE
            } else {
                set1.fillColor = Color.WHITE
            }
            set1.setDrawValues(true)
            val dataSets: ArrayList<ILineDataSet> = ArrayList()
            dataSets.add(set1)
            val data = LineData(dataSets)
            volumeReportChart.data = data
        }
    }
}