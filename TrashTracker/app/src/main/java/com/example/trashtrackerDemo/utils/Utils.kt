package com.example.trashtrackerDemo.utils

import android.os.Build
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun getDateInMilliSeconds(givenDateString: String?, format: String): Long {
        val sdf = SimpleDateFormat(format, Locale.US)
        var timeInMilliseconds: Long = 1
        try {
            val mDate: Date? = givenDateString?.let { sdf.parse(it) }
            timeInMilliseconds = mDate!!.time
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return timeInMilliseconds
    }

    fun getSDKInt(): Int {
        return Build.VERSION.SDK_INT
    }
}