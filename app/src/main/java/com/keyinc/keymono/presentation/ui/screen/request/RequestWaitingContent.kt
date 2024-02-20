package com.keyinc.keymono.presentation.ui.screen.request

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
import com.keyinc.keymono.presentation.ui.theme.FontSmall
import com.keyinc.keymono.presentation.ui.theme.InterLabelBold
import com.keyinc.keymono.presentation.ui.theme.InterLogo
import com.keyinc.keymono.presentation.ui.theme.Padding24
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge

@Composable
fun RequestWaitingContent(
    onClick: () -> Unit = {},
    paddingValues: PaddingValues
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
            onClick = onClick
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