package com.keyinc.keymono.presentation.model

import com.keyinc.keymono.domain.entity.ScheduleStatus
import java.time.LocalDateTime

data class ScheduleElement(
    val startTimeDisplay: String,
    val startDateTime: LocalDateTime,
    val endTimeDisplay: String,
    val endDateTime: LocalDateTime,
    val status: ScheduleStatus
)
