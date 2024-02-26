package com.sukacolab.app.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.convertToMonthYearFormat(): String {
    // Parsing tanggal ke objek Date
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormat.parse(this)

    // Format tanggal ke format "nama_bulan tahun"
    val monthYearFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    return monthYearFormat.format(date)
}

fun String.convertToYearMonthDayFormat(): String {
    // Parsing tanggal ke objek Date
    val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
    val date = dateFormat.parse(this)

    // Format tanggal ke format "tahun-bulan-tanggal"
    val yearMonthDayFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return yearMonthDayFormat.format(date)
}

fun String.convertToOriginalFormat(): String {
    // Parsing tanggal ke objek Date
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = dateFormat.parse(this)

    // Format tanggal ke format "EEE MMM dd HH:mm:ss zzz yyyy"
    val originalFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
    return originalFormat.format(date)
}

fun String.convertToDate(): Date? {
    return try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        dateFormat.parse(this)
    } catch (e: Exception) {
        null
    }
}
