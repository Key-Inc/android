package com.keyinc.keymono.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserKeyDto(
    val id: String,
    val classroom: ClassroomDto,
)
