package com.keyinc.keymono.presentation.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.state.UserState
import com.keyinc.keymono.presentation.ui.navigation.Routes
import com.keyinc.keymono.presentation.ui.theme.InterLogo
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import com.keyinc.keymono.presentation.viewModel.SplashViewModel

@Composable
fun SplashScreen(onNavigateToOnBoarding: (String) -> Unit, splashViewModel: SplashViewModel) {

    val userState = splashViewModel.userState.collectAsStateWithLifecycle()

    when (userState.value) {
        UserState.Initial -> {
            splashViewModel.isUserLoggedIn()
        }
        UserState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
        UserState.UserLoggedIn -> {
            splashViewModel.getRequestStatus()
        }
        UserState.UserNotLoggedIn -> {
            onNavigateToOnBoarding(Routes.FirstRegistrationScreen.route)
        }
        UserState.RequestConfirmed -> {
            onNavigateToOnBoarding(Routes.RequestWaitingScreen.route)
        }
        UserState.Idling -> {
            onNavigateToOnBoarding(Routes.RequestWaitingScreen.route)
        }

        is UserState.Error -> {
            Text("Error occurred")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            painter = painterResource(id = R.drawable.vpn_key),
            contentDescription = stringResource(id = R.string.company_logo)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = PaddingMedium),
            text = stringResource(id = R.string.company_label),
            style = InterLogo,
            textAlign = TextAlign.Center
        )

    }
}