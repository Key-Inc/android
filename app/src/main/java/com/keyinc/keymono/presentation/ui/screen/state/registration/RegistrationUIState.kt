package com.keyinc.keymono.presentation.ui.screen.state.registration

import com.keyinc.keymono.domain.entity.ValidationResult
import com.keyinc.keymono.presentation.ui.util.PresentationConstants.EMPTY_STRING

data class RegistrationUIState(
    var email: String = EMPTY_STRING,
    var fullName: String = EMPTY_STRING,
    var birthDate: String = EMPTY_STRING,
    var phoneNumber: String = EMPTY_STRING,
    var password: String = EMPTY_STRING,
    var confirmPassword: String = EMPTY_STRING,
    var fullNameValidation: ValidationResult = ValidationResult(
        successful = false,
        errorId = null
    ),
    var phoneNumberValidation: ValidationResult = ValidationResult(
        successful = false,
        errorId = null
    ),
    var passwordValidation: ValidationResult = ValidationResult(
        successful = false,
        errorId = null
    ),
    var confirmPasswordValidation: ValidationResult = ValidationResult(
        successful = false,
        errorId = null
    ),
    var emailValidation: ValidationResult = ValidationResult(
        successful = false,
        errorId = null
    ),
    var firstSectionPassed: Boolean = false,
    var secondSectionPassed: Boolean = false
)

fun RegistrationUIState.firstValidationIsPassed() {
    firstSectionPassed =
        emailValidation.successful && passwordValidation.successful && confirmPasswordValidation.successful
}

fun RegistrationUIState.secondValidationIsPassed() {
    secondSectionPassed =
        birthDate.isNotEmpty() && fullNameValidation.successful && phoneNumberValidation.successful
}