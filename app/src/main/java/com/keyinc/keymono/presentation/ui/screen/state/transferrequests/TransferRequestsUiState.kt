package com.keyinc.keymono.presentation.ui.screen.state.transferrequests

import com.keyinc.keymono.domain.entity.TransferRequests

sealed class TransferRequestsUiState {

    data object Initial : TransferRequestsUiState()

    data object Loading : TransferRequestsUiState()

    data class Error(val message: String) : TransferRequestsUiState()

    data class Success(val content: TransferRequests) : TransferRequestsUiState()

}
