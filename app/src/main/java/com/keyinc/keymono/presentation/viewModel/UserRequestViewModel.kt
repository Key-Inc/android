package com.keyinc.keymono.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.keymono.domain.usecase.request.GetUserRequestUseCase
import com.keyinc.keymono.presentation.ui.errorHandler.RequestExceptionHandler
import com.keyinc.keymono.presentation.ui.screen.state.userrequest.UserRequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserRequestViewModel @Inject constructor(private val getUserRequestUseCase: GetUserRequestUseCase) :
    ViewModel() {

    private val _requestState = MutableStateFlow<UserRequestState>(UserRequestState.Initial)
    val requestState = _requestState.asStateFlow()

    private val exceptionHandler = RequestExceptionHandler(
        onUnauthorizedException = {
            _requestState.value = UserRequestState.Unauthorized
        },
        onBaseException = {
            _requestState.value = UserRequestState.Error
        },
        onBadRegistrationRequest = {
            _requestState.value =
                UserRequestState.Error
        }
    )

    fun restoreState() {
        _requestState.value = UserRequestState.Initial
    }

    fun getUserRequest() {
        _requestState.value = UserRequestState.Loading
        viewModelScope.launch(Dispatchers.IO + exceptionHandler.coroutineExceptionHandler) {
            val content = getUserRequestUseCase.invoke()
            if (content.isEmpty()) {
                _requestState.value = UserRequestState.NoRequest
            }
            _requestState.value = UserRequestState.Content(getUserRequestUseCase.invoke())
        }
    }

}