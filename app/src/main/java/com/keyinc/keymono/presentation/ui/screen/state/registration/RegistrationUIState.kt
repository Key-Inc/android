package com.keyinc.keymono.presentation.ui.screen.state.registration

import com.keyinc.keymono.presentation.ui.util.PresentationConstants.EMPTY_STRING

data class RegistrationUIState(
    var email: String = EMPTY_STRING,
    var emailErrorId: Int? = null,
    var fullName: String = EMPTY_STRING,
    var birthDate: String = EMPTY_STRING,
    var phoneNumber: String = EMPTY_STRING,
    var password: String = EMPTY_STRING,
    var passwordErrorId: Int? = null,
    var confirmPassword: String = EMPTY_STRING,
    var confirmPasswordErrorId: Int? = null,
    var firstSectionPassed: Boolean = false
)

fun RegistrationUIState.firstRegistrationSectionIsCorrect(): Boolean {
    return listOf(passwordErrorId, confirmPasswordErrorId, emailErrorId).all { it == null }
}