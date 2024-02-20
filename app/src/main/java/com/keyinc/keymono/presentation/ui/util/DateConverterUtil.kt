package com.keyinc.keymono.presentation.ui.util

import java.time.LocalDate

object DateConverterUtil {

    fun convertDateToString(date: LocalDate?): String {
        return date?.let {
            val day = it.dayOfMonth.toString().padStart(2, '0')
            val month = it.monthValue.toString().padStart(2, '0')
            val year = it.year.toString()
            "$day.$month.$year"
        } ?: ""
    }

}