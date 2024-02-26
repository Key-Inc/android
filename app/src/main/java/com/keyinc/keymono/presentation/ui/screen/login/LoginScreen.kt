package com.keyinc.keymono.presentation.ui.screen.login

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.component.AccentButton
import com.keyinc.keymono.presentation.ui.component.AccentTextField
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.FontSmall
import com.keyinc.keymono.presentation.ui.theme.InterLabelBold
import com.keyinc.keymono.presentation.ui.theme.InterLogo
import com.keyinc.keymono.presentation.ui.theme.Padding24
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.util.noRippleClickable
import com.keyinc.keymono.presentation.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    // TODO change to onNavigateToRequests
    onNavigateToClassroomChoice: () -> Unit,
    onNavigateToRegister: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    // TODO handle ui state
    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
            .verticalScroll(rememberScrollState())
            .padding(start = PaddingLarge, end = PaddingLarge),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.login_label),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = InterLabelBold
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TODO validate (change errorId)
            AccentTextField(
                textFieldValue = loginState.email,
                label = stringResource(id = R.string.email),
                errorId = null,
                onValueChange = viewModel::onEmailChanged
            )
            Spacer(modifier = Modifier.padding(20.dp))
            AccentTextField(
                textFieldValue = loginState.password,
                label = stringResource(id = R.string.password),
                errorId = null,
                onValueChange = viewModel::onPasswordChanged
            )
        }

        Spacer(modifier = Modifier.padding(Padding24))
        AccentButton(
            enabled = true,
            onClick = {
                viewModel.loginUser()
                onNavigateToClassroomChoice()
            },
            text = stringResource(id = R.string.log_in_short)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Padding24, top = PaddingLarge),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.no_account),
                style = InterLogo,
                fontSize = FontSmall,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.noRippleClickable { onNavigateToRegister() },
                text = stringResource(id = R.string.register),
                style = InterLogo,
                fontSize = FontSmall,
                color = Accent,
                textAlign = TextAlign.Center,
            )
        }
    }
}