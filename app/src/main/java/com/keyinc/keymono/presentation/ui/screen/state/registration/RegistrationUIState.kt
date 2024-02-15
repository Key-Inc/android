package com.keyinc.keymono.presentation.ui.screen.state.registration

import com.keyinc.keymono.presentation.ui.util.PresentationConstants.EMPTY_STRING

data class RegistrationUIState(
    var email: String = EMPTY_STRING,
    var fullName: String = EMPTY_STRING,
    var birthDate: String = EMPTY_STRING,
    var phoneNumber: String = EMPTY_STRING,
    var password: String = EMPTY_STRING,
    var confirmPassword: String = EMPTY_STRING
)

fun RegistrationUIState.isAllFieldEmpty(): Boolean {
    return listOf(email, fullName, birthDate, phoneNumber, password, confirmPassword).all { it.isEmpty() }
}