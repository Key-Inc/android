package com.keyinc.keymono.presentation.ui.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DateConverterUtil {

    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    fun convertDateToString(date: LocalDate?): String {
        return date?.let {
            val day = it.dayOfMonth.toString().padStart(2, '0')
            val month = it.monthValue.toString().padStart(2, '0')
            val year = it.year.toString()
            "$day.$month.$year"
        } ?: ""
    }


    fun convertDateToServerFormat(date: String): String {
        val dateParts = date.split(".")
        return "${dateParts[2]}-${dateParts[1]}-${dateParts[0]}"
    }

    // Converts "HH:mm:ss" to "HH:mm"
    fun convertTimeToHoursAndMinutes(time: String)
        = time.split(":").take(2).joinToString(":")

    // Joins LocalDate with time in "HH:mm:ss" format into LocalDateTime
    fun joinLocalDateAndStringTime(localDate: LocalDate, timeString: String): LocalDateTime {
        val localTime = LocalTime.parse(timeString, timeFormatter)
        return LocalDateTime.of(localDate, localTime)
    }

    fun changeTimeInLocalDateTime(localDateTime: LocalDateTime, time: LocalTime): LocalDateTime {
        return LocalDateTime.of(localDateTime.toLocalDate(), time)
    }

}