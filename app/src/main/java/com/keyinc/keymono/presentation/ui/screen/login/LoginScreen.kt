package com.keyinc.keymono.presentation.ui.screen.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.keyinc.keymono.presentation.ui.screen.state.login.LoginUiState
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.FontSmall
import com.keyinc.keymono.presentation.ui.theme.InterLabelBold
import com.keyinc.keymono.presentation.ui.theme.InterLogo
import com.keyinc.keymono.presentation.ui.theme.Padding24
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import com.keyinc.keymono.presentation.ui.theme.PaddingSmall
import com.keyinc.keymono.presentation.ui.util.noRippleClickable
import com.keyinc.keymono.presentation.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    onNavigateToRequest: () -> Unit,
    onNavigateToClassroomChoice: () -> Unit,
    onNavigateToRegister: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var loginError: String? = null
    var buttonClick: () -> Unit = {}
    var buttonContent: @Composable (() -> Unit)? = null
    val focusManager = LocalFocusManager.current
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()
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
            AccentTextField(
                textFieldValue = loginState.email,
                label = stringResource(id = R.string.email),
                errorId = loginState.emailValidation,
                onValueChange = viewModel::onEmailChanged
            )
            Spacer(modifier = Modifier.padding(20.dp))
            AccentTextField(
                textFieldValue = loginState.password,
                label = stringResource(id = R.string.password),
                errorId = loginState.passwordValidation,
                onValueChange = viewModel::onPasswordChanged
            )
        }


        when (loginUiState) {

            is LoginUiState.RegistrationPassed -> {
                viewModel.getRegistrationStatus()
            }

            is LoginUiState.Initial -> {
                buttonClick = { viewModel.loginUser() }
            }

            is LoginUiState.Loading -> {
                buttonContent = {
                    CircularProgressIndicator(
                        modifier = Modifier.size(PaddingMedium),
                        color = Color.White,
                        strokeWidth = 3.dp,
                    )
                }
            }

            is LoginUiState.Success -> {
                onNavigateToClassroomChoice()
            }

            is LoginUiState.Error -> {
                loginError = (loginUiState as LoginUiState.Error).message
                buttonClick = { viewModel.loginUser() }
            }

            is LoginUiState.InConsideration -> {
                onNavigateToRequest()
            }

            is LoginUiState.Accepted -> {
                viewModel.getUserRole()
            }

            is LoginUiState.WrongRole -> {
                loginError = stringResource(id = R.string.wrong_role_error)
                buttonClick = { viewModel.loginUser() }
            }

        }

        Spacer(modifier = Modifier.padding(Padding24))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AccentButton(
                enabled = loginState.validationIsPassed,
                onClick = buttonClick,
                text = stringResource(id = R.string.log_in_short)
            ) {
                buttonContent?.invoke()
            }
            AnimatedVisibility(visible = loginError != null) {
                Text(
                    modifier = Modifier.padding(top = PaddingSmall),
                    text = loginError ?: "",
                    style = InterLogo,
                    fontSize = FontSmall,
                    color = Color.Red,
                )
            }
        }

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