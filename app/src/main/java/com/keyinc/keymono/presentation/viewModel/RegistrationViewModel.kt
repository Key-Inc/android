package com.keyinc.keymono.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.keyinc.keymono.R
import com.keyinc.keymono.domain.usecase.validation.ValidateConfirmPasswordUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidateEmailUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidatePasswordUseCase
import com.keyinc.keymono.presentation.ui.screen.state.registration.RegistrationUIState
import com.keyinc.keymono.presentation.ui.screen.state.registration.firstRegistrationSectionIsCorrect
import com.keyinc.keymono.presentation.ui.util.PresentationConstants.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateConfirmPasswordUseCase: ValidateConfirmPasswordUseCase
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationUIState())
    val uiState = _uiState.asStateFlow()

    private val _registrationState = MutableStateFlow(EMPTY_STRING)
    val registrationState: StateFlow<String>
        get() = _registrationState


    fun onEmailChanged(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            emailErrorId = validateEmailUseCase(
                validationProperty = email,
                errorId = R.string.email_error
            ).errorId,
            firstSectionPassed = isFirstRegistrationSectionPassed()
        )
        Log.d("RegistrationViewModel", "onConfirmPasswordChanged: ${_uiState.value.firstSectionPassed}")
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            passwordErrorId = validatePasswordUseCase(
                validationProperty = password,
                errorId = R.string.password_error
            ).errorId,
            firstSectionPassed = isFirstRegistrationSectionPassed()
        )
        Log.d("RegistrationViewModel", "onConfirmPasswordChanged: ${_uiState.value.firstSectionPassed}")
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
            ).errorId,
            firstSectionPassed = isFirstRegistrationSectionPassed()
        )
        Log.d("RegistrationViewModel", "onConfirmPasswordChanged: ${_uiState.value.firstSectionPassed}")
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        _uiState.value = _uiState.value.copy(phoneNumber = phoneNumber)
    }

    private fun isFirstRegistrationSectionPassed(): Boolean {
        return _uiState.value.firstRegistrationSectionIsCorrect() &&
                _uiState.value.email.isNotBlank() &&
                _uiState.value.password.isNotBlank() &&
                _uiState.value.confirmPassword.isNotBlank()
    }

}