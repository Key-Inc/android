package com.keyinc.keymono.domain.entity

import java.util.UUID

data class Classroom(
    val id: UUID,
    val number: Int,
    val building: Int
)
