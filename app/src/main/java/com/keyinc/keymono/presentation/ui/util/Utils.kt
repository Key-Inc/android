package com.keyinc.keymono.presentation.ui.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
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

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = this.then(
    composed {
        clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick()
        }
    }
)