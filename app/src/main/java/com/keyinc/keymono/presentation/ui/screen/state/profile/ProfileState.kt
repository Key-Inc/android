package com.keyinc.keymono.presentation.ui.screen.state.profile

import com.keyinc.keymono.domain.entity.ValidationResult
import com.keyinc.keymono.presentation.ui.util.PresentationConstants.EMPTY_STRING

data class ProfileState(
    val fullName: String = EMPTY_STRING,
    val role: String = EMPTY_STRING,
    val email: String = EMPTY_STRING,
    val dateOfBirth: String = EMPTY_STRING,
    val phoneNumber: String = EMPTY_STRING,

    var emailValidation: ValidationResult = ValidationResult(
        successful = true,
        errorId = null
    ),
    var phoneNumberValidation: ValidationResult = ValidationResult(
        successful = true,
        errorId = null
    ),

    val canSaveNewProfile: Boolean = false
)
