package com.keyinc.keymono.presentation.ui.screen.state.registration

sealed class RegistrationState {
    data object Initial : RegistrationState()
    data object Loading : RegistrationState()
    data object Success : RegistrationState()
    data object UnauthorizedError : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}