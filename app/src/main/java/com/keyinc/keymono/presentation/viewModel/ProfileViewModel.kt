package com.keyinc.keymono.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.keymono.R
import com.keyinc.keymono.domain.entity.UserEditDto
import com.keyinc.keymono.domain.usecase.account.EditProfileUseCase
import com.keyinc.keymono.domain.usecase.account.GetProfileUseCase
import com.keyinc.keymono.domain.usecase.account.LogoutUserUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidateEmailUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidatePhoneNumberUseCase
import com.keyinc.keymono.presentation.ui.errorHandler.RequestExceptionHandler
import com.keyinc.keymono.presentation.ui.screen.state.profile.ProfileState
import com.keyinc.keymono.presentation.ui.screen.state.profile.ProfileUiState
import com.keyinc.keymono.presentation.ui.util.DateConverterUtil.convertDateToUiFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val editProfileUseCase: EditProfileUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase
) : ViewModel() {

    private var initialProfile: ProfileState? = null

    private val _profileState = MutableStateFlow(ProfileState())
    val profileState: StateFlow<ProfileState>
        get() = _profileState

    private val _profileUiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Initial)
    val profileUiState: StateFlow<ProfileUiState>
        get() = _profileUiState

    private val exceptionHandler = RequestExceptionHandler(
        onUnauthorizedException = {
            _profileUiState.value = ProfileUiState.Error("Ошибка авторизации")
        },
        onBaseException = {
            _profileUiState.value = ProfileUiState.Error("Неизвестная ошибка")
        },
        onBadRegistrationRequest = {
            _profileUiState.value =
                ProfileUiState.Error("Не удалось обновить данные")
        }
    )

    init {
        loadUserProfile()
    }


    fun logoutUser() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler.coroutineExceptionHandler) {
            logoutUserUseCase.execute()
        }
    }

    private fun loadUserProfile() {
        _profileUiState.value = ProfileUiState.Loading
        viewModelScope.launch(Dispatchers.IO + exceptionHandler.coroutineExceptionHandler) {
            val profile = getProfileUseCase()
            with(profile) {
                _profileState.value = ProfileState(
                    fullName = fullName,
                    role = userRole.description,
                    email = email,
                    dateOfBirth = convertDateToUiFormat(birthDate),
                    phoneNumber = phoneNumber.substring(2)
                )
            }
            initialProfile = _profileState.value
            _profileUiState.value = ProfileUiState.Initial
        }
    }

    fun editProfile() {
        _profileUiState.value = ProfileUiState.Loading
        viewModelScope.launch(Dispatchers.IO + exceptionHandler.coroutineExceptionHandler) {
            editProfileUseCase(
                profile = with (_profileState.value) {
                    UserEditDto(
                        birthDate = convertDateToServerFormat(dateOfBirth),
                        phoneNumber = phoneNumber,
                        email = email
                    )
                }
            )
        }
        initialProfile = _profileState.value
        _profileUiState.value = ProfileUiState.Success
    }

    fun onEmailChanged(email: String) {
        _profileState.value = _profileState.value.copy(
            email = email,
            emailValidation = validateEmailUseCase(
                validationProperty = email,
                errorId = R.string.email_error
            )
        )

        updateCanSaveNewProfile()
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        _profileState.value = _profileState.value.copy(
            phoneNumber = phoneNumber,
            phoneNumberValidation = validatePhoneNumberUseCase(
                validationProperty = phoneNumber,
                errorId = R.string.phone_number_error
            )
        )

        updateCanSaveNewProfile()
    }

    fun onDateOfBirthChanged(dateOfBirth: String) {
        _profileState.value = _profileState.value.copy(dateOfBirth = dateOfBirth)
        updateCanSaveNewProfile()
    }

    fun cancelEditing() {
        val initialProfile = this.initialProfile
        if (initialProfile != null) {
            _profileState.value = initialProfile
        }
    }

    private fun updateCanSaveNewProfile() {
        _profileState.value = _profileState.value.copy(
            canSaveNewProfile = isProfileDataValid() && isProfileDataChanged()
        )
    }

    private fun isProfileDataChanged(): Boolean {
        val initialProfile = this.initialProfile
        return initialProfile != null &&
                with(_profileState.value) {
                    (initialProfile.email != email ||
                            initialProfile.phoneNumber != phoneNumber ||
                            initialProfile.dateOfBirth != dateOfBirth)
                }
    }

    private fun isProfileDataValid(): Boolean {
        return with(profileState.value) {
            emailValidation.successful && phoneNumberValidation.successful
        }
    }

    private fun convertDateToServerFormat(date: String): String {
        val dateParts = date.split(".")
        return "${dateParts[0]}-${dateParts[1]}-${dateParts[2]}"
    }

}