package com.keyinc.keymono.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.FontSmall
import com.keyinc.keymono.presentation.ui.theme.InterLabelBold
import com.keyinc.keymono.presentation.ui.theme.LightBlue
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import com.keyinc.keymono.presentation.ui.theme.PaddingSmall
import com.keyinc.keymono.presentation.ui.theme.PaddingTiny

@Composable
fun AccentPasswordTextField(
    modifier: Modifier = Modifier,
    textFieldValue: String,
    label: String,
    singleLine: Boolean = true,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val visibilityIconState = if (isPasswordVisible) {
        ImageVector.vectorResource(id = R.drawable.eye_close)

    } else {
        ImageVector.vectorResource(id = R.drawable.eye_open)
    }

    Text(
        text = label,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = PaddingSmall, bottom = PaddingTiny),
        style = InterLabelBold,
        fontSize = FontSmall,
        color = Color.Black
    )
    BasicTextField(
        modifier = modifier
            .background(
                color = LightBlue,
                shape = RoundedCornerShape(PaddingSmall)
            )
            .border(
                width = 3.dp,
                color = Accent,
                shape = RoundedCornerShape(PaddingSmall)
            )
            .fillMaxWidth(),
        value = textFieldValue,
        onValueChange = onValueChange,
        textStyle = InterLabelBold,
        keyboardOptions = keyboardOptions,
        visualTransformation = if (!isPasswordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        singleLine = singleLine,
        enabled = true,
        cursorBrush = SolidColor(Color.Black),
        decorationBox = @Composable { innerTextField ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(PaddingMedium),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                innerTextField()
                Icon(
                    imageVector = visibilityIconState,
                    modifier = Modifier
                        .clickable(onClick = {
                            isPasswordVisible = !isPasswordVisible
                        })
                        .size(PaddingLarge),
                    contentDescription = null,
                )
            }
        }
    )
}
