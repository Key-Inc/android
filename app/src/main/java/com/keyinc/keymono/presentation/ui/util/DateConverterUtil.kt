package com.keyinc.keymono.presentation.ui.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DateConverterUtil {

    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    private val toServerDateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    fun convertDateToString(date: LocalDate?): String {
        return date?.let {
            val day = it.dayOfMonth.toString().padStart(2, '0')
            val month = it.monthValue.toString().padStart(2, '0')
            val year = it.year.toString()
            "$day.$month.$year"
        } ?: ""
    }


    fun extractTime(inputDateTime: String): String {
        val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
        val outputFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val dateTime = LocalDateTime.parse(inputDateTime, inputFormatter)
        return dateTime.format(outputFormatter)
    }

    fun convertToRequestDateFormat(inputDate: String): String {
        val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
        val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val date = LocalDateTime.parse(inputDate, inputFormatter)
        return date.format(outputFormatter)
    }

    fun convertDateWithoutTime(inputDate: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val date = LocalDate.parse(inputDate, inputFormatter)
        return date.format(outputFormatter)
    }


    fun convertDateToServerFormat(date: String): String {
        val dateParts = date.split(".")
        return "${dateParts[2]}-${dateParts[1]}-${dateParts[0]}"
    }

    // Converts "HH:mm:ss" to "HH:mm"
    fun convertTimeToHoursAndMinutes(time: String) = time.split(":").take(2).joinToString(":")

    // Joins LocalDate with time in "HH:mm:ss" format into LocalDateTime
    fun joinLocalDateAndStringTime(localDate: LocalDate, timeString: String): LocalDateTime {
        val localTime = LocalTime.parse(timeString, timeFormatter)
        return LocalDateTime.of(localDate, localTime)
    }

    fun changeTimeInLocalDateTime(localDateTime: LocalDateTime, time: LocalTime): LocalDateTime {
        return LocalDateTime.of(localDateTime.toLocalDate(), time)
    }

    fun toServerLocalDateTime(localDateTime: LocalDateTime): String =
        toServerDateTimeFormatter.format(localDateTime)

}