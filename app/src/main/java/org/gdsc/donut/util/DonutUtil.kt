package org.gdsc.donut.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar

class DonutUtil {
    @SuppressLint("SimpleDateFormat")
    fun getDDayInfo(date: String): Long {
        val systemDate = Calendar.getInstance()
        val eventDate = SimpleDateFormat("yyyy-MM-dd").parse(date)
        return (systemDate.time.time - eventDate.time) / (60 * 60 * 24 * 1000) - 1
    }

    @SuppressLint("SimpleDateFormat")
    fun setCalendarFormat(date: String): String? {
        val parsedDate = SimpleDateFormat("yyyy-MM-dd").parse(date)
        return parsedDate?.let { SimpleDateFormat("MMM d").format(it) }
    }
}