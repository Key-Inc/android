package com.keyinc.keymono.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.FontMedium
import com.keyinc.keymono.presentation.ui.theme.InterLogo
import com.keyinc.keymono.presentation.ui.theme.Tomato

@Composable
fun AccentButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    text: String,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(61.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Accent,
            contentColor = Color.White
        )
    ) {
        content()
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = InterLogo,
            fontSize = FontMedium
        )
    }
}


@Composable
fun TomatoAccentButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    text: String,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(61.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Tomato,
            contentColor = Color.White
        )
    ) {
        content()
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = InterLogo,
            fontSize = FontMedium
        )
    }
}