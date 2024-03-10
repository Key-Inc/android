package com.keyinc.keymono.presentation.ui.screen.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.theme.PaddingSmall

@Composable
fun ProfileItems(
    role: String,
    email: String,
    phone: String,
    dateOfBirth: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PaddingSmall)
    ) {
        ProfileItem(
            iconVector = ImageVector.vectorResource(id = R.drawable.ic_person),
            text = role
        )
        ProfileItem(
            iconVector = ImageVector.vectorResource(id = R.drawable.ic_mail),
            text = email
        )
        ProfileItem(
            iconVector = ImageVector.vectorResource(id = R.drawable.ic_phone),
            text = phone
        )
        ProfileItem(
            iconVector = ImageVector.vectorResource(id = R.drawable.ic_calendar),
            text = dateOfBirth
        )
    }
}