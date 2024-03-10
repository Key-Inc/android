package com.keyinc.keymono.presentation.ui.screen.state.newrequest

sealed class NewRequestUiState {
    data object Initial : NewRequestUiState()
    data object Loading : NewRequestUiState()
    data class Error(val errorMessage: String? = null) : NewRequestUiState()
    data object Success : NewRequestUiState()
}