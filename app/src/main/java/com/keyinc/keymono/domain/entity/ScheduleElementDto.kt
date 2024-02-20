package com.keyinc.keymono.domain.entity

import java.time.LocalDateTime

data class ScheduleElementDto(
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val status: ScheduleStatus
)