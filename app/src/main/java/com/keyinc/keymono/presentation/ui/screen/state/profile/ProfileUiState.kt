package com.keyinc.keymono.presentation.ui.screen.state.profile

sealed class ProfileUiState {

    data object Initial : ProfileUiState()

    data object Loading : ProfileUiState()

    data class Error(val message: String) : ProfileUiState()

    data object Success : ProfileUiState()
}
