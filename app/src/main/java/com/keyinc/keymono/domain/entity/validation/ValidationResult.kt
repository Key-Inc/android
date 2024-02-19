package com.keyinc.keymono.domain.entity.validation

data class ValidationResult (
    val successful: Boolean,
    val errorMessage: String? = null,
)