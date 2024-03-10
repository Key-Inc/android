package com.keyinc.keymono.presentation.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.component.AccentButton
import com.keyinc.keymono.presentation.ui.component.SecondaryButton
import com.keyinc.keymono.presentation.ui.screen.profile.components.ProfileItems
import com.keyinc.keymono.presentation.ui.theme.Padding64
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import com.keyinc.keymono.presentation.ui.theme.PaddingSmall
import com.keyinc.keymono.presentation.ui.theme.ProfileRegular
import com.keyinc.keymono.presentation.ui.theme.Title

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 95.dp, bottom = PaddingSmall),
            text = stringResource(id = R.string.your_profile),
            style = Title,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.profile_greetings, "Змеев Денис Олегович"),
            style = ProfileRegular,
            textAlign = TextAlign.Center
        )

        ProfileItems(
            modifier = Modifier.padding(vertical = Padding64, horizontal = PaddingMedium),
            role = "Преподаватель",
            email = "zmeev@gmail.com",
            phone = "88005553535",
            dateOfBirth = "31.02.1990"
        )

        AccentButton(
            enabled = true,
            modifier = Modifier.padding(horizontal = PaddingLarge),
            text = stringResource(id = R.string.change_data),
            onClick = { /* TODO */ }
        )

        Spacer(modifier = Modifier.height(PaddingMedium))

        SecondaryButton(
            modifier = Modifier.padding(horizontal = PaddingLarge),
            text = stringResource(id = R.string.request_history),
            onClick = { /* TODO */ }
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}