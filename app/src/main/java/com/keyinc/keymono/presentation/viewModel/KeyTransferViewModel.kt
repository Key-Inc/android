package com.keyinc.keymono.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.keymono.domain.usecase.key.GetUserAvailableKeysUseCase
import com.keyinc.keymono.presentation.ui.errorHandler.RequestExceptionHandler
import com.keyinc.keymono.presentation.ui.screen.state.keytransfer.KeyTransferState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class KeyTransferViewModel @Inject constructor(private val getUserAvailableKeysUseCase: GetUserAvailableKeysUseCase) :
    ViewModel() {

    private val _keysTransferState = MutableStateFlow<KeyTransferState>(KeyTransferState.Initial)
    val keysTransferState = _keysTransferState.asStateFlow()


    private val exceptionHandler = RequestExceptionHandler(
        onUnauthorizedException = {
            _keysTransferState.value = KeyTransferState.Unauthorized
        },
        onBaseException = {
            _keysTransferState.value = KeyTransferState.Error
        },
        onBadRegistrationRequest = {
            _keysTransferState.value =
                KeyTransferState.Error
        }
    )


    fun restoreState() {
        _keysTransferState.value = KeyTransferState.Initial
    }

    fun getUserAvailableKeys() {
        _keysTransferState.value = KeyTransferState.Loading
        viewModelScope.launch(Dispatchers.IO + exceptionHandler.coroutineExceptionHandler) {
            val content = getUserAvailableKeysUseCase.invoke()
            if (content.isEmpty()) {
                _keysTransferState.value = KeyTransferState.NoKeys
            } else {
                _keysTransferState.value = KeyTransferState.Content(content)
            }
        }
    }

}