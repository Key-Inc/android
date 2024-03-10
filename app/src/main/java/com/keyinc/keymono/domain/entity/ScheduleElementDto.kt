package com.keyinc.keymono.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleElementDto(
    val startDate: String,
    val endDate: String,
    val status: ScheduleStatus
)