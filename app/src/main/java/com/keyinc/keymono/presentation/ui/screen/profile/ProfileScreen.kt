package com.keyinc.keymono.presentation.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.keyinc.keymono.presentation.viewModel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onNavigateToEditProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    val profileState by viewModel.profileState.collectAsStateWithLifecycle()

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
            text = stringResource(id = R.string.profile_greetings, profileState.fullName),
            style = ProfileRegular,
            textAlign = TextAlign.Center
        )

        ProfileItems(
            modifier = Modifier.padding(vertical = Padding64, horizontal = PaddingMedium),
            role = profileState.role,
            email = profileState.email,
            phone = "+7${profileState.phoneNumber}",
            dateOfBirth = profileState.dateOfBirth
        )

        AccentButton(
            modifier = Modifier.padding(horizontal = PaddingLarge),
            text = stringResource(id = R.string.change_data),
            onClick = onNavigateToEditProfile,
            enabled = true
        )

        Spacer(modifier = Modifier.height(PaddingMedium))

        SecondaryButton(
            modifier = Modifier.padding(horizontal = PaddingLarge),
            text = stringResource(id = R.string.request_history),
            onClick = { /* TODO */ }
        )
    }
}