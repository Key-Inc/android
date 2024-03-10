package com.keyinc.keymono.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class KeyRequestCreateDto(
    val startDate: String,
    val endDate: String,
    val endDateOfRecurrence: String?,
    val classroomId: String
)
