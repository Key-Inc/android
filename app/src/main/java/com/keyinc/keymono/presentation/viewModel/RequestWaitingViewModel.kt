package com.keyinc.keymono.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.keymono.R
import com.keyinc.keymono.domain.usecase.account.GetUserRequestStatus
import com.keyinc.keymono.domain.usecase.account.GetUserRoleUseCase
import com.keyinc.keymono.presentation.ui.errorHandler.RequestExceptionHandler
import com.keyinc.keymono.presentation.ui.screen.state.requestWaiting.RequestWaitingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestWaitingViewModel @Inject constructor(
    private val getUserRequestStatus: GetUserRequestStatus,
    private val getUserRoleUseCase: GetUserRoleUseCase
) :
    ViewModel() {

    private val _requestState = MutableStateFlow<RequestWaitingState>(RequestWaitingState.Initial)
    val requestState = _requestState.asStateFlow()

    private val exceptionHandler = RequestExceptionHandler(
        onUnauthorizedException = {
            _requestState.value = RequestWaitingState.Unauthorized
        },
        onBaseException = {
            _requestState.value = RequestWaitingState.Error(R.string.unexpected_error)
        },
        onBadRegistrationRequest = {
            _requestState.value =
                RequestWaitingState.Error(R.string.unexpected_error)
        }
    )

    fun getUserRole() {
        _requestState.value = RequestWaitingState.Loading
        viewModelScope.launch(Dispatchers.IO + exceptionHandler.coroutineExceptionHandler) {
            val userRole = getUserRoleUseCase.execute()
            when (userRole) {
                "Student", "Teacher" -> _requestState.value = RequestWaitingState.Success
                else -> _requestState.value = RequestWaitingState.WrongRole
            }
        }
    }


    fun getUserRegistrationStatus() {
        _requestState.value = RequestWaitingState.Loading
        viewModelScope.launch(Dispatchers.IO + exceptionHandler.coroutineExceptionHandler) {
            val registrationStatus = getUserRequestStatus.invoke()
            when (registrationStatus) {
                true -> _requestState.value = RequestWaitingState.Accepted
                false -> _requestState.value =
                    RequestWaitingState.Error(R.string.request_is_under_consideration)
            }
        }
    }

}