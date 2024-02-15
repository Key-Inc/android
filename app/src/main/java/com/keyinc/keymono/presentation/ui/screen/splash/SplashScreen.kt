package com.keyinc.keymono.presentation.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.theme.InterLogo
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToOnBoarding: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(1000L)
        onNavigateToOnBoarding.invoke()
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