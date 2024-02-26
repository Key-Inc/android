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
fun IsRecurringField(
    isRecurring: Boolean,
    onChangeRecurring: () -> Unit,
    modifier: Modifier = Modifier
) {
    val trailingIconId = if (isRecurring) R.drawable.ic_checkbox_checked else R.drawable.ic_checkbox_unchecked

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = stringResource(id = R.string.recurring),
        onValueChange = {},
        enabled = false,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_repeat),
                contentDescription = null
            )
        },
        trailingIcon = {
            IconButton(
                modifier = Modifier.padding(end = PaddingSmall),
                onClick = onChangeRecurring
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = trailingIconId),
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