package com.keyinc.keymono.presentation.ui.screen.state.login

import com.keyinc.keymono.domain.entity.ValidationResult
import com.keyinc.keymono.presentation.ui.util.PresentationConstants.EMPTY_STRING

data class LoginState(
    var email: String = EMPTY_STRING,
    var emailValidation: ValidationResult = ValidationResult(
        successful = false,
        errorId = null
    ),
    var password: String = EMPTY_STRING,
    var passwordValidation: ValidationResult = ValidationResult(
        successful = false,
        errorId = null
    ),
    var validationIsPassed: Boolean = false
)

fun LoginState.validationIsPassed() {
    validationIsPassed = passwordValidation.successful && emailValidation.successful
}