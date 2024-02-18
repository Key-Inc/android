package com.keyinc.keymono.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.FontSmall
import com.keyinc.keymono.presentation.ui.theme.InterLabelBold
import com.keyinc.keymono.presentation.ui.theme.LightBlue
import com.keyinc.keymono.presentation.ui.theme.PaddingSmall
import com.keyinc.keymono.presentation.ui.theme.PaddingTiny


@Composable
fun AccentTextField(
    modifier: Modifier = Modifier,
    textFieldValue: String,
    label: String,
    singleLine: Boolean = true,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {

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
        singleLine = singleLine,
        enabled = true,
        cursorBrush = SolidColor(Color.Black)
    )
    { innerTextField ->
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            innerTextField()
        }
    }
}
