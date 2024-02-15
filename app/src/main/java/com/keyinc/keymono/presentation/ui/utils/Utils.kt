package com.keyinc.keymono.presentation.ui.utils

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

private val dateFormatter = DateTimeFormatter.ofPattern("dd")

fun formatCalendarDay(date: LocalDate): String = dateFormatter.format(date)

fun DayOfWeek.displayText(capitalize: Boolean = true): String {
    val defaultLocale = Locale.getDefault()
    return getDisplayName(TextStyle.SHORT, defaultLocale).let { day ->
        if (capitalize)
            day.replaceFirstChar { if (it.isLowerCase()) it.titlecase(defaultLocale) else it.toString() }
        else
            day
    }
}