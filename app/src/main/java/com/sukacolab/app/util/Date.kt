package com.sukacolab.app.util

import java.text.SimpleDateFormat
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
