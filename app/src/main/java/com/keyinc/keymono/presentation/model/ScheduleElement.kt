package com.keyinc.keymono.presentation.model

import com.keyinc.keymono.domain.entity.ScheduleStatus

data class ScheduleElement(
    val startTime: String,
    val endTime: String,
    val status: ScheduleStatus
)
