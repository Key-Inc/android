package com.keyinc.keymono.presentation.ui.screen.registration.firstpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.component.AccentPasswordTextField
import com.keyinc.keymono.presentation.ui.component.AccentTextField
import com.keyinc.keymono.presentation.ui.component.PhoneTextField
import com.keyinc.keymono.presentation.ui.screen.state.registration.RegistrationUIState
import com.keyinc.keymono.presentation.viewModel.RegistrationViewModel

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
        )
        Spacer(modifier = Modifier.padding(20.dp))
        AccentPasswordTextField(
            textFieldValue = uiState.password,
            onValueChange = { registrationViewModel.onPasswordChanged(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = stringResource(id = R.string.password),
        )
        Spacer(modifier = Modifier.padding(20.dp))
        AccentPasswordTextField(
            textFieldValue = uiState.confirmPassword,
            onValueChange = { registrationViewModel.onConfirmPasswordChanged(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = stringResource(id = R.string.confirm_password),
        )
    }
}


@Composable
fun RegistrationSecondSection(
    registrationViewModel: RegistrationViewModel,
    uiState: RegistrationUIState
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AccentTextField(
            textFieldValue = uiState.fullName,
            onValueChange = { registrationViewModel.onFullNameChanged(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = stringResource(id = R.string.fullname),
        )
        Spacer(modifier = Modifier.padding(20.dp))
        AccentTextField(
            textFieldValue = uiState.birthDate,
            onValueChange = { registrationViewModel.onPasswordChanged(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = stringResource(id = R.string.birth_date),
        )
        Spacer(modifier = Modifier.padding(20.dp))
        PhoneTextField(
            textFieldValue = uiState.phoneNumber,
            onValueChange = { registrationViewModel.onPhoneNumberChanged(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            label = stringResource(id = R.string.phone_number),
        )
    }
}