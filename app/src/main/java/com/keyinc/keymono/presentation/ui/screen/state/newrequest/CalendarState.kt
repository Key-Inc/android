package com.keyinc.keymono.presentation.ui.screen.state.newrequest

import java.time.LocalDate

data class CalendarState(
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = startDate.plusDays(DATE_RANGE_DAYS),
    val selectedDate: LocalDate = startDate
)

private const val DATE_RANGE_DAYS = 6L