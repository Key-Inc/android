package com.keyinc.keymono.presentation.ui.screen.profile

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.component.AccentButton
import com.keyinc.keymono.presentation.ui.component.AccentClickableElement
import com.keyinc.keymono.presentation.ui.component.AccentTextField
import com.keyinc.keymono.presentation.ui.component.DatePicker
import com.keyinc.keymono.presentation.ui.component.PhoneTextField
import com.keyinc.keymono.presentation.ui.screen.state.profile.ProfileUiState
import com.keyinc.keymono.presentation.ui.theme.Padding20
import com.keyinc.keymono.presentation.ui.theme.Padding64
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.theme.Title
import com.keyinc.keymono.presentation.viewModel.ProfileViewModel
import java.time.LocalDate

@Composable
fun EditProfileScreen(
    viewModel: ProfileViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val profileUiState by viewModel.profileUiState.collectAsStateWithLifecycle()
    val profileState by viewModel.profileState.collectAsStateWithLifecycle()
    var isDatePickerOpened by remember { mutableStateOf(false) }

    BackHandler {
        viewModel.cancelEditing()
        onNavigateBack()
    }

    Column(
        modifier = modifier.padding(horizontal = PaddingLarge),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 95.dp, bottom = Padding20),
            text = stringResource(id = R.string.edit_profile_label),
            style = Title,
            textAlign = TextAlign.Center
        )

        AccentTextField(
            textFieldValue = profileState.email,
            onValueChange = viewModel::onEmailChanged,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = stringResource(id = R.string.email),
            errorId = profileState.emailValidation
        )

        Spacer(modifier = Modifier.height(Padding20))

        AccentClickableElement(
            date = profileState.dateOfBirth,
            onOpenSelection = { isDatePickerOpened = true }
        )

        Spacer(modifier = Modifier.height(Padding20))

        PhoneTextField(
            textFieldValue = profileState.phoneNumber,
            onValueChange = viewModel::onPhoneNumberChanged,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            label = stringResource(id = R.string.phone_number),
        )

        Spacer(modifier = Modifier.height(Padding64))

        AccentButton(
            modifier = Modifier.padding(horizontal = PaddingLarge),
            text = stringResource(id = R.string.update_data),
            onClick = viewModel::editProfile,
            enabled = profileState.canSaveNewProfile
        )

        when (profileUiState) {
            ProfileUiState.Initial -> Unit
            ProfileUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            ProfileUiState.Success -> {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(id = R.string.edit_profile_successful),
                    Toast.LENGTH_SHORT
                ).show()
                // TODO remove toast (or add one-time event) and navigate to main screen
            }

            is ProfileUiState.Error -> {
                // TODO
                Log.e("EditProfileScreen", (profileUiState as ProfileUiState.Error).message)
            }
        }

        if (!isDatePickerOpened) return
        DatePicker(
            onCloseSelection = {
                isDatePickerOpened = false
            },
            onDateChange = viewModel::onDateOfBirthChanged,
            isInReversedFormat = true,
            maxYear = LocalDate.now().year,
            minYear = LocalDate.now().year - 100
        )
    }
}