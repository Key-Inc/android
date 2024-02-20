package com.keyinc.keymono.presentation.ui.screen.newrequest.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.LightGray
import com.keyinc.keymono.presentation.ui.theme.PaddingSmall
import com.keyinc.keymono.presentation.ui.theme.RequestFieldText

@Composable
fun TimeField(
    isStartingTime: Boolean,
    time: String,
    modifier: Modifier = Modifier
) {
    val iconId = if (isStartingTime) R.drawable.ic_keyboard_backspace_forward else R.drawable.ic_keyboard_backspace

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = time,
        onValueChange = {},
        enabled = false,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = iconId),
                contentDescription = stringResource(id = R.string.booking_start_time)
            )
        },
        trailingIcon = {
            IconButton(
                modifier = Modifier.padding(end = PaddingSmall),
                onClick = { /*TODO navigate to time choice screen*/ }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_edit),
                    tint = Accent,
                    contentDescription = stringResource(id = R.string.edit)
                )
            }
        },
        colors = TextFieldDefaults.colors(
            disabledContainerColor = LightGray,
            disabledTextColor = Color.Black,
            disabledLeadingIconColor = Color.Black
        ),
        textStyle = RequestFieldText
    )
}