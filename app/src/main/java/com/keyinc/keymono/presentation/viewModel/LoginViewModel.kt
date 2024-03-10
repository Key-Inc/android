package com.keyinc.keymono.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.keymono.R
import com.keyinc.keymono.domain.entity.LoginRequest
import com.keyinc.keymono.domain.usecase.account.LoginUserUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidateEmailUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidatePasswordUseCase
import com.keyinc.keymono.presentation.ui.errorHandler.RequestExceptionHandler
import com.keyinc.keymono.presentation.ui.screen.state.login.LoginState
import com.keyinc.keymono.presentation.ui.screen.state.login.LoginUiState
import com.keyinc.keymono.presentation.ui.screen.state.login.validationIsPassed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Initial)
    val loginUiState = _loginUiState.asStateFlow()

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    private val exceptionHandler = RequestExceptionHandler(
        onUnauthorizedException = {
            _loginUiState.value = LoginUiState.Error("Неверный логин или пароль")
        },
        onBaseException = {
            _loginUiState.value = LoginUiState.Error("Неизвестная ошибка")
        },
        onBadRegistrationRequest = {
            _loginUiState.value =
                LoginUiState.Error("Пользователь с таким email уже существует")
        }
    )

    fun loginUser() {
        _loginUiState.value = LoginUiState.Loading
        viewModelScope.launch(Dispatchers.IO + exceptionHandler.coroutineExceptionHandler) {
            loginUserUseCase(
                LoginRequest(
                    email = _loginState.value.email,
                    password = _loginState.value.password
                )
            )
            _loginUiState.value = LoginUiState.Success
        }
    }

    fun onEmailChanged(email: String) {
        _loginState.value = _loginState.value.copy(
            email = email,
            emailValidation = validateEmailUseCase(
                validationProperty = email,
                errorId = R.string.email_error
            )
        )
        _loginState.value.validationIsPassed()
    }

    fun onPasswordChanged(password: String) {
        _loginState.value = _loginState.value.copy(
            password = password,
            passwordValidation = validatePasswordUseCase(
                validationProperty = password,
                errorId = R.string.password_error
            ),
        )
        _loginState.value.validationIsPassed()
    }

}