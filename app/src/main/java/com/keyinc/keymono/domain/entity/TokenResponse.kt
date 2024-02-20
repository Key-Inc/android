package com.keyinc.keymono.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val token: String
)
