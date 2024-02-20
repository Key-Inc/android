package com.keyinc.keymono.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val id: String,
    val fullName: String,
    val birthDate: String,
    val gender: Gender,
    val phoneNumber: String,
    val email: String,
    val userRole: UserRole
)


