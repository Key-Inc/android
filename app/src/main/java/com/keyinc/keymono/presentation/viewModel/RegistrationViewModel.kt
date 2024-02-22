package com.keyinc.keymono.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.keymono.R
import com.keyinc.keymono.domain.entity.RegistrationRequest
import com.keyinc.keymono.domain.usecase.account.RegisterUserUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidateConfirmPasswordUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidateEmailUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidatePasswordUseCase
import com.keyinc.keymono.presentation.ui.screen.state.registration.RegistrationState
import com.keyinc.keymono.presentation.ui.screen.state.registration.RegistrationUIState
import com.keyinc.keymono.presentation.ui.util.DateConverterUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val registrationUseCase: RegisterUserUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateConfirmPasswordUseCase: ValidateConfirmPasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationUIState())
    val uiState = _uiState.asStateFlow()

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Initial)
    val registrationState: StateFlow<RegistrationState>
        get() = _registrationState


    fun registerUser() {
        viewModelScope.launch(Dispatchers.IO) {
            _registrationState.value = RegistrationState.Loading
            try {
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
            catch (e: Exception) {
                //TODO replace hardcoded string to resource
                _registrationState.value = RegistrationState.Error(e.message ?: "Unknown error")
            }
        }
    }


    fun onEmailChanged(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            emailErrorId = validateEmailUseCase(
                validationProperty = email,
                errorId = R.string.email_error
            ).errorId
        )
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            passwordErrorId = validatePasswordUseCase(
                validationProperty = password,
                errorId = R.string.password_error
            ).errorId
        )
    }

    fun onBirthDateChanged(birthDate: String) {
        _uiState.value = _uiState.value.copy(birthDate = birthDate)
    }

    fun onFullNameChanged(fullName: String) {
        _uiState.value = _uiState.value.copy(fullName = fullName)
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = confirmPassword,
            confirmPasswordErrorId = validateConfirmPasswordUseCase(
                password = _uiState.value.password,
                validationProperty = confirmPassword,
                errorId = R.string.confirm_password_error
            ).errorId
        )
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        _uiState.value = _uiState.value.copy(phoneNumber = phoneNumber)
    }


}