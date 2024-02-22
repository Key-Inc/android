package com.keyinc.keymono.presentation.ui.screen.state.login

sealed class LoginUiState {
    data object Initial : LoginUiState()
    data object Loading : LoginUiState()
    data object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}
