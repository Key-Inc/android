package com.keyinc.keymono.presentation.ui.screen.request

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.component.AccentButton
import com.keyinc.keymono.presentation.ui.screen.state.requestWaiting.RequestWaitingState
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.FontSmall
import com.keyinc.keymono.presentation.ui.theme.InterLabelBold
import com.keyinc.keymono.presentation.ui.theme.InterLogo
import com.keyinc.keymono.presentation.ui.theme.Padding24
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import com.keyinc.keymono.presentation.viewModel.RequestWaitingViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestWaitingScreen(
    onNavigateToClassroomChoice: () -> Unit,
    onNavigateToLogin: () -> Unit,
    requestWaitingViewModel: RequestWaitingViewModel = hiltViewModel()
) {
    var buttonClick: () -> Unit = {}
    var buttonContent: @Composable (() -> Unit)? = null
    val requestWaitingState by requestWaitingViewModel.requestState.collectAsStateWithLifecycle()
    var requestError: String? = null


    when (requestWaitingState) {

        is RequestWaitingState.Initial -> {
            buttonClick = { requestWaitingViewModel.getUserRegistrationStatus() }
        }

        is RequestWaitingState.Loading -> {
            buttonContent = {
                CircularProgressIndicator(
                    modifier = Modifier.size(PaddingMedium),
                    color = Color.White,
                    strokeWidth = 3.dp,
                )
            }
        }

        is RequestWaitingState.Error -> {
            requestError =
                stringResource((requestWaitingState as RequestWaitingState.Error).message)
            buttonClick = { requestWaitingViewModel.getUserRegistrationStatus() }
        }

        is RequestWaitingState.Unauthorized -> {
            onNavigateToLogin()
        }

        is RequestWaitingState.Success -> {
            onNavigateToClassroomChoice()
        }

        is RequestWaitingState.Accepted -> {
            requestWaitingViewModel.getUserRole()
        }

        is RequestWaitingState.WrongRole -> {
            requestError = stringResource(R.string.wrong_role_error)
        }


    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = stringResource(id = R.string.app_label))
            })
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.cardinal_logo),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )

                    Text(
                        text = stringResource(id = R.string.on_during_request),
                        style = InterLabelBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(
                                top = Padding24,
                                start = PaddingLarge,
                                end = PaddingLarge
                            )
                            .fillMaxWidth()
                    )

                    AccentButton(
                        modifier = Modifier.padding(
                            top = PaddingLarge,
                            start = PaddingLarge,
                            end = PaddingLarge
                        ),
                        text = stringResource(id = R.string.check_status),
                        onClick = buttonClick,
                        enabled = true
                    ) {
                        buttonContent?.invoke()
                    }


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
                            text = stringResource(id = R.string.log_in),
                            modifier = Modifier.clickable { onNavigateToLogin(); requestWaitingViewModel.logout() },
                            style = InterLogo,
                            fontSize = FontSmall,
                            color = Accent,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                AnimatedVisibility(
                    visible = requestError != null,
                    enter = slideInVertically(
                        initialOffsetY = { fullHeight -> -fullHeight },
                        animationSpec = tween(durationMillis = 300)
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { fullHeight -> -fullHeight },
                        animationSpec = tween(durationMillis = 400)
                    )
                ) {
                    RequestAlert(
                        text = requestError
                            ?: stringResource(id = R.string.request_is_under_consideration)
                    )
                    LaunchedEffect(key1 = requestError) {
                        delay(3000L)
                        requestError = null
                    }
                }
            }
        }
    )
}