package com.keyinc.keymono.presentation.ui.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.component.AccentButton
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.FontNormal
import com.keyinc.keymono.presentation.ui.theme.FontSmall
import com.keyinc.keymono.presentation.ui.theme.InterLabelBold
import com.keyinc.keymono.presentation.ui.theme.InterLogo
import com.keyinc.keymono.presentation.ui.theme.Padding24
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium


@Composable
fun OnBoardingContent(
    paddingValues: PaddingValues,
    onNavigateToRegistration: () -> Unit,
    onNavigateToLogin: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.cat_logo),
            contentDescription = null
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.onboard_label),
                modifier = Modifier
                    .padding(
                        top = Padding24,
                        start = PaddingLarge,
                        end = PaddingLarge
                    )
                    .fillMaxWidth(),
                style = InterLabelBold,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.onboard_desc),
                modifier = Modifier
                    .padding(
                        top = PaddingMedium,
                        start = PaddingLarge,
                        end = PaddingLarge
                    )
                    .fillMaxWidth(),
                style = InterLogo,
                textAlign = TextAlign.Center,
                fontSize = FontNormal
            )

        }

        AccentButton(
            modifier = Modifier.padding(
                top = PaddingLarge,
                start = PaddingLarge,
                end = PaddingLarge
            ),
            text = stringResource(id = R.string.onboard_button),
            onClick = { onNavigateToRegistration() }
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
                text = stringResource(id = R.string.log_in),
                style = InterLogo,
                fontSize = FontSmall,
                color = Accent,
                textAlign = TextAlign.Center,
            )

        }
    }

}