package com.keyinc.keymono.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.keymono.domain.usecase.transfer.GetTransferRequestsUseCase
import com.keyinc.keymono.presentation.ui.errorHandler.RequestExceptionHandler
import com.keyinc.keymono.presentation.ui.screen.state.transferrequests.TransferRequestsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferRequestsViewModel @Inject constructor(
    private val getTransferRequestsUseCase: GetTransferRequestsUseCase
) : ViewModel() {

    private val _transferRequestsUiState = MutableStateFlow<TransferRequestsUiState>(TransferRequestsUiState.Initial)
    val transferRequestsUiState: StateFlow<TransferRequestsUiState>
        get() = _transferRequestsUiState

    private val exceptionHandler = RequestExceptionHandler(
        onUnauthorizedException = {
            _transferRequestsUiState.value = TransferRequestsUiState.Error("Ошибка авторизации")
        },
        onBaseException = {
            _transferRequestsUiState.value = TransferRequestsUiState.Error("Неизвестная ошибка")
        },
        onBadRegistrationRequest = {
            _transferRequestsUiState.value = TransferRequestsUiState.Error("Неизвестная ошибка")
        }
    )

    init {
        getTransferRequests()
    }

    private fun getTransferRequests() {
        _transferRequestsUiState.value = TransferRequestsUiState.Loading
        viewModelScope.launch(Dispatchers.IO + exceptionHandler.coroutineExceptionHandler) {
            val transferRequests = getTransferRequestsUseCase()
            _transferRequestsUiState.value = TransferRequestsUiState.Success(transferRequests)
        }
    }

}