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
import com.keyinc.keymono.presentation.ui.theme.DodgerBlue
import com.keyinc.keymono.presentation.ui.theme.InterLogo
import com.keyinc.keymono.presentation.ui.theme.mediumFont

@Composable
fun AccentButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {}
) {

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(61.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = DodgerBlue,
            contentColor = Color.White
        )
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = InterLogo,
            fontSize = mediumFont
        )
    }
}