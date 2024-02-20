package com.keyinc.keymono.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.FontSmall
import com.keyinc.keymono.presentation.ui.theme.InterLabelBold
import com.keyinc.keymono.presentation.ui.theme.LightBlue
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import com.keyinc.keymono.presentation.ui.theme.PaddingSmall
import com.keyinc.keymono.presentation.ui.theme.PaddingTiny
import com.keyinc.keymono.presentation.ui.util.DateConverterUtil
import java.time.LocalDate


@Composable
fun AccentClickableElement(value: LocalDate?, onOpenSelection: () -> Unit) {
    Text(
        text = stringResource(id = R.string.birth_date),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = PaddingSmall, bottom = PaddingTiny),
        style = InterLabelBold,
        fontSize = FontSmall,
        color = Color.Black
    )
    Box(
        modifier = Modifier
            .clickable { onOpenSelection() }
            .background(
                color = LightBlue,
                shape = RoundedCornerShape(PaddingSmall)
            )
            .border(
                width = 3.dp,
                color = Accent,
                shape = RoundedCornerShape(PaddingSmall)
            )
            .fillMaxWidth()
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingMedium)
        ) {
            Text(
                text = DateConverterUtil.convertDateToString(value),
                style = InterLabelBold,
            )
        }
    }
}