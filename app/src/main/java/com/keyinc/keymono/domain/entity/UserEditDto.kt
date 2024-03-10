package com.keyinc.keymono.domain.entity

data class UserEditDto(
    val birthDate: String,
    val phoneNumber: String? = null,
    val email: String? = null,
    val password: String? = null
)
