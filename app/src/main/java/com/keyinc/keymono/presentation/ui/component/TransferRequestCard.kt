package com.keyinc.keymono.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.CalendarDayOfWeek
import com.keyinc.keymono.presentation.ui.theme.CardBackGround
import com.keyinc.keymono.presentation.ui.theme.FontMedium
import com.keyinc.keymono.presentation.ui.theme.FontNormal
import com.keyinc.keymono.presentation.ui.theme.Regular

@Composable
fun TransferRequestCard(
    classRoomName: String = "220 (2) аудитория",
    applicantName: String = "Максим Журавлёв"
) {

    Box(modifier = Modifier.fillMaxWidth()) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 16.dp, end = 32.dp),
            colors = CardDefaults.cardColors(

                containerColor = CardBackGround
            )
        ) {
            Text(
                text = classRoomName,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                style = Regular,
                fontSize = FontMedium
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Передал: ",
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, bottom = 16.dp),
                    style = CalendarDayOfWeek,
                    color = Color.Black,
                    fontSize = FontNormal
                )
                Text(
                    text = applicantName,
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                    style = CalendarDayOfWeek,
                    color = Accent,
                    fontSize = FontNormal
                )
            }
        }
    }
}

@Preview
@Composable
private fun TransferRequestCardPreview() {
    TransferRequestCard()
}

