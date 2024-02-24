package com.keyinc.keymono.domain.entity

import java.time.LocalDateTime
import java.util.UUID

data class KeyRequestCreateDto(
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val isRecurring: Boolean,
    val classroomId: UUID
)
