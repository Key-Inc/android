package com.keyinc.keymono.presentation.state

sealed class RegistrationState {
    data object Initial : RegistrationState()
    data object Loading : RegistrationState()
    data object Success : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}