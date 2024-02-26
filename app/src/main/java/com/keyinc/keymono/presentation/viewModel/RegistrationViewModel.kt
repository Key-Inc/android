package com.keyinc.keymono.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.keymono.R
import com.keyinc.keymono.domain.entity.RegistrationRequest
import com.keyinc.keymono.domain.usecase.account.RegisterUserUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidateConfirmPasswordUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidateEmailUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidateFullNameUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidatePasswordUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidatePhoneNumberUseCase
import com.keyinc.keymono.presentation.ui.errorHandler.RequestExceptionHandler
import com.keyinc.keymono.presentation.ui.screen.state.registration.RegistrationState
import com.keyinc.keymono.presentation.ui.screen.state.registration.RegistrationUIState
import com.keyinc.keymono.presentation.ui.screen.state.registration.firstValidationIsPassed
import com.keyinc.keymono.presentation.ui.screen.state.registration.secondValidationIsPassed
import com.keyinc.keymono.presentation.ui.util.DateConverterUtil
import com.keyinc.keymono.presentation.ui.util.NetworkErrorCodes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val registrationUseCase: RegisterUserUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateConfirmPasswordUseCase: ValidateConfirmPasswordUseCase,
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val validateFullNameUseCase: ValidateFullNameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationUIState())
    val uiState: StateFlow<RegistrationUIState>
        get() = _uiState

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Initial)
    val registrationState: StateFlow<RegistrationState>
        get() = _registrationState

    private val exceptionHandler = RequestExceptionHandler(
        onUnauthorizedException = {
            _registrationState.value = RegistrationState.Error("Ошибка авторизации")
        },
        onBaseException = {
            _registrationState.value = RegistrationState.Error("Неизвестная ошибка")
        },
        onBadRegistrationRequest = {
            _registrationState.value =
                RegistrationState.Error("Пользователь с таким email уже существует")
        }
    )


    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                NetworkErrorCodes.UNAUTHORIZED -> _registrationState.value =
                    RegistrationState.Error("Ошибка авторизации")

                NetworkErrorCodes.BAD_REQUEST -> {
                    val errorResponse = exception.response()?.errorBody()?.string()
                    if (errorResponse != null) {
                        if (errorResponse.contains("User")) _registrationState.value =
                            RegistrationState.Error("Пользователь с таким email уже существует")
                    } else _registrationState.value = RegistrationState.Error("Неизвестная ошибка")
                }

                else -> _registrationState.value = RegistrationState.Error("Неизвестная ошибка")
            }

            else -> _registrationState.value = RegistrationState.Error("Неизвестная ошибка")

        }
    }


    fun registerUser() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _registrationState.value = RegistrationState.Loading
            registrationUseCase(
                RegistrationRequest(
                    email = _uiState.value.email,
                    fullName = _uiState.value.fullName,
                    password = _uiState.value.password,
                    birthDate = DateConverterUtil.convertDateToServerFormat(
                        _uiState.value.birthDate
                    ),
                    phoneNumber = _uiState.value.phoneNumber
                )
            )
            _registrationState.value = RegistrationState.Success
        }
    }


    fun onEmailChanged(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            emailValidation = validateEmailUseCase(
                validationProperty = email,
                errorId = R.string.email_error
            )
        )
        _uiState.value.firstValidationIsPassed()
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            passwordValidation = validatePasswordUseCase(
                validationProperty = password,
                errorId = R.string.password_error
            ),
            confirmPasswordValidation = validateConfirmPasswordUseCase(
                password = password,
                validationProperty = _uiState.value.confirmPassword,
                errorId = R.string.confirm_password_error
            )
        )
        _uiState.value.firstValidationIsPassed()
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = confirmPassword,
            confirmPasswordValidation = validateConfirmPasswordUseCase(
                password = _uiState.value.password,
                validationProperty = confirmPassword,
                errorId = R.string.confirm_password_error
            )
        )
        _uiState.value.firstValidationIsPassed()
    }

    fun onBirthDateChanged(birthDate: String) {
        _uiState.value = _uiState.value.copy(birthDate = birthDate)
        _uiState.value.secondValidationIsPassed()
        Log.d("RegistrationViewModel", _uiState.value.secondSectionPassed.toString())
    }

    fun onFullNameChanged(fullName: String) {
        _uiState.value = _uiState.value.copy(
            fullName = fullName,
            fullNameValidation = validateFullNameUseCase(
                validationProperty = fullName,
                errorId = R.string.fullname_error
            )
        )
        _uiState.value.secondValidationIsPassed()
        Log.d("RegistrationViewModel", _uiState.value.secondSectionPassed.toString())
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        _uiState.value = _uiState.value.copy(
            phoneNumber = phoneNumber,
            phoneNumberValidation = validatePhoneNumberUseCase(
                validationProperty = phoneNumber,
                errorId = R.string.phone_number_error
            )
        )
        _uiState.value.secondValidationIsPassed()
        Log.d("RegistrationViewModel", _uiState.value.secondSectionPassed.toString())
    }
}
