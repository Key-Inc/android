package com.keyinc.keymono.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.keymono.domain.entity.LoginRequest
import com.keyinc.keymono.domain.usecase.account.LoginUserUseCase
import com.keyinc.keymono.presentation.ui.screen.state.login.LoginState
import com.keyinc.keymono.presentation.ui.screen.state.login.LoginUiState
import com.keyinc.keymono.presentation.ui.util.NetworkErrorCodes.UNAUTHORIZED
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
): ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Initial)
    val loginUiState = _loginUiState.asStateFlow()

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    private val loginExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                UNAUTHORIZED -> _loginUiState.value = LoginUiState.Error(exception.message ?: "Unknown exception")
                else -> _loginUiState.value = LoginUiState.Error(exception.message ?: "Unknown exception")
            }
            else -> _loginUiState.value = LoginUiState.Error(exception.message ?: "Unknown exception")
        }
    }

    fun loginUser() {
        _loginUiState.value = LoginUiState.Loading
        viewModelScope.launch(Dispatchers.IO + loginExceptionHandler) {
            loginUserUseCase(
                LoginRequest(
                    email = _loginState.value.email,
                    password = _loginState.value.password
                )
            )
            _loginUiState.value = LoginUiState.Success
        }
    }

    // TODO validate all the fields
    fun onEmailChanged(email: String) {
        _loginState.value = _loginState.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _loginState.value = _loginState.value.copy(password = password)
    }

}