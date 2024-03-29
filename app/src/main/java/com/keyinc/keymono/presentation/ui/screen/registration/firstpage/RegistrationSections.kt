package com.keyinc.keymono.presentation.ui.screen.registration.firstpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.component.AccentClickableElement
import com.keyinc.keymono.presentation.ui.component.AccentPasswordTextField
import com.keyinc.keymono.presentation.ui.component.AccentTextField
import com.keyinc.keymono.presentation.ui.component.DatePicker
import com.keyinc.keymono.presentation.ui.component.PhoneTextField
import com.keyinc.keymono.presentation.ui.screen.state.registration.RegistrationUIState
import com.keyinc.keymono.presentation.ui.theme.Padding20
import com.keyinc.keymono.presentation.viewModel.RegistrationViewModel
import java.time.LocalDate

@Composable
fun RegistrationFirstSection(
    registrationViewModel: RegistrationViewModel,
    uiState: RegistrationUIState
) {


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AccentTextField(
            textFieldValue = uiState.email,
            onValueChange = { registrationViewModel.onEmailChanged(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = stringResource(id = R.string.email),
            errorId = uiState.emailValidation
        )
        Spacer(modifier = Modifier.padding(Padding20))
        AccentPasswordTextField(
            textFieldValue = uiState.password,
            onValueChange = { registrationViewModel.onPasswordChanged(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = stringResource(id = R.string.password),
            errorId = uiState.passwordValidation
        )
        Spacer(modifier = Modifier.padding(Padding20))
        AccentPasswordTextField(
            textFieldValue = uiState.confirmPassword,
            onValueChange = { registrationViewModel.onConfirmPasswordChanged(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = stringResource(id = R.string.confirm_password),
            errorId = uiState.confirmPasswordValidation
        )
    }
}


@Composable
fun RegistrationSecondSection(
    registrationViewModel: RegistrationViewModel,
    uiState: RegistrationUIState
) {
    var datePickerState by remember {
        mutableStateOf(false)
    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AccentTextField(
            textFieldValue = uiState.fullName,
            onValueChange = { registrationViewModel.onFullNameChanged(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = stringResource(id = R.string.fullname),
            errorId = uiState.fullNameValidation
        )
        Spacer(modifier = Modifier.padding(Padding20))
        AccentClickableElement(
            date = uiState.birthDate,
            onOpenSelection = { datePickerState = true })
        Spacer(modifier = Modifier.padding(Padding20))
        PhoneTextField(
            textFieldValue = uiState.phoneNumber,
            onValueChange = { registrationViewModel.onPhoneNumberChanged(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            label = stringResource(id = R.string.phone_number),
        )
    }

    if (datePickerState) {
        DatePicker(onCloseSelection = {
            datePickerState = false
        },
            onDateChange = {
                registrationViewModel.onBirthDateChanged(it)
            },
            minYear = LocalDate.now().year - 100,
            maxYear = LocalDate.now().year
        )
    }
}