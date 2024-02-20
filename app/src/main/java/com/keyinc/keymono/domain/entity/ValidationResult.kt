package com.keyinc.keymono.domain.entity

data class ValidationResult(
    val successful: Boolean,
    val errorId: Int? = null,
)


