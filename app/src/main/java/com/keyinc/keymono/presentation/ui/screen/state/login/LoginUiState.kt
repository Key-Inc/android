package com.keyinc.keymono.presentation.ui.screen.state.login

sealed class LoginUiState {
    data object Initial : LoginUiState()
    data object RegistrationPassed: LoginUiState()
    data object Loading : LoginUiState()
    data object Accepted : LoginUiState()
    data object InConsideration: LoginUiState()
    data object WrongRole : LoginUiState()
    data object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}
