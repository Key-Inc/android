package com.keyinc.keymono.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(
    val fullName: String,
    val password: String,
    val birthDate: String,
    val gender: Gender = Gender.MALE,
    val phoneNumber: String,
    val email: String
)


