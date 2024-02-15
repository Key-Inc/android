package com.keyinc.keymono.presentation.ui.screen.request

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.presentation.ui.theme.Tomato
import com.keyinc.keymono.presentation.ui.theme.largePadding

@Composable
fun RequestAlert(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(121.dp)
            .fillMaxWidth()
            .padding(start = largePadding, end = largePadding)
            .clip(RoundedCornerShape(12.dp)),

        colors = CardDefaults.cardColors(
            containerColor = Tomato,
            contentColor = Color.White
        )
    ) {

    }
}