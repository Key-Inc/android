package com.keyinc.keymono.presentation.ui.screen.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.component.AccentButton
import com.keyinc.keymono.presentation.ui.screen.registration.firstpage.RegistrationFirstSection
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.FontSmall
import com.keyinc.keymono.presentation.ui.theme.InterLabelBold
import com.keyinc.keymono.presentation.ui.theme.InterLogo
import com.keyinc.keymono.presentation.ui.theme.Padding24
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.util.noRippleClickable
import com.keyinc.keymono.presentation.viewModel.RegistrationViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstRegistrationScreen(
    registrationViewModel: RegistrationViewModel,
    onNavigateToBack: () -> Unit,
    onNavigateToSecondPart: () -> Unit,
    onNavigateToLogin: () -> Unit
) {

    val focusManager = LocalFocusManager.current
    val uiState by registrationViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateToBack) {
                        Image(
                            painter = painterResource(id = R.drawable.back_button_icon),
                            contentDescription = stringResource(id = R.string.navigate_back_button_description)
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.app_label))
                })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
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
                    text = stringResource(id = R.string.first_registration_label),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = InterLabelBold
                )
                RegistrationFirstSection(
                    registrationViewModel = registrationViewModel,
                    uiState = uiState
                )
                Spacer(modifier = Modifier.padding(Padding24))
                AccentButton(
                    enabled = uiState.firstSectionPassed,
                    onClick = onNavigateToSecondPart,
                    text = stringResource(id = R.string.next)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Padding24, top = PaddingLarge),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.already_have_account),
                        style = InterLogo,
                        fontSize = FontSmall,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.noRippleClickable { onNavigateToLogin() },
                        text = stringResource(id = R.string.log_in),
                        style = InterLogo,
                        fontSize = FontSmall,
                        color = Accent,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    )
}


