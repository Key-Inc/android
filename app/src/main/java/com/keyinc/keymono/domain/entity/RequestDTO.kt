package com.keyinc.keymono.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class RequestDTO(
    val id: String,
    val status: String,
    val startDate: String,
    val endDate: String,
    val classroom: ClassroomDto,
    val endDateOfRecurrence: String
)

@Serializable
data class ClassroomDto(
    val id: String,
    val number: Int,
    val building: Int
)


