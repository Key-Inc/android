package com.keyinc.keymono.presentation.ui.screen.state.newrequest

import java.time.LocalDateTime
import java.util.UUID

data class NewRequestState(
    val startDate: LocalDateTime? = null,
    val endDate: LocalDateTime? = null,
    val endDateOfRecurrence: String? = null,
    val classroomId: UUID? = null,
    val classroomNumber: Int? = null
) {
    val isRecurring = endDateOfRecurrence != null
}