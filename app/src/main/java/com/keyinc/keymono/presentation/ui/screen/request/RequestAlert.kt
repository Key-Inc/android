package com.keyinc.keymono.presentation.ui.screen.request

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keyinc.keymono.presentation.ui.theme.InterLabelBold
import com.keyinc.keymono.presentation.ui.theme.Padding24
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import com.keyinc.keymono.presentation.ui.theme.Tomato

@Composable
fun RequestAlert(
    modifier: Modifier = Modifier,
    text: String,
) {
    Card(
        modifier = modifier
            .height(121.dp)
            .fillMaxWidth()
            .padding(start = PaddingMedium, end = PaddingMedium)
            .clip(RoundedCornerShape(12.dp)),

        colors = CardDefaults.cardColors(
            containerColor = Tomato,
            contentColor = Color.White
        ),
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Padding24),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                modifier = Modifier.fillMaxSize(),
                style = InterLabelBold,
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}