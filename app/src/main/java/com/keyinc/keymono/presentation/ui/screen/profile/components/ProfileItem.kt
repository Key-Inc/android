package com.keyinc.keymono.presentation.ui.screen.profile.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.PaddingSmall
import com.keyinc.keymono.presentation.ui.theme.Regular

@Composable
fun ProfileItem(
    iconVector: ImageVector,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = iconVector,
            tint = Accent,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(PaddingSmall))

        Text(
            text = text,
            style = Regular
        )
    }
}