package com.keyinc.keymono.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.CalendarDayOfWeek
import com.keyinc.keymono.presentation.ui.theme.CardBackGround
import com.keyinc.keymono.presentation.ui.theme.FontMedium
import com.keyinc.keymono.presentation.ui.theme.FontNormal
import com.keyinc.keymono.presentation.ui.theme.FontSmall
import com.keyinc.keymono.presentation.ui.theme.Regular
import com.keyinc.keymono.presentation.ui.theme.Title

@Composable
fun TransferRequestCard(
    onApprove: () -> Unit,
    onReject: () -> Unit,
    keyId: String,
    classRoomName: String = "220 (2) аудитория",
    applicantName: String = "Максим Журавлёв"
) {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        KeyTransferDialog(
            onApprove = { onApprove() },
            onReject = { onReject() },
            onDismiss = { showDialog = false }
        )
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDialog = true }
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


@Composable
fun KeyTransferDialog(
    onApprove: () -> Unit,
    onReject: () -> Unit,
    onDismiss: () -> Unit
) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            surface = Color.Black,
            onSurface = Color.Black
        )
    ) {
        Dialog(onDismissRequest = {}) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Передача ключа",
                    style = Title,
                    textAlign = TextAlign.Center,
                    fontSize = FontMedium
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Вы уверены, что хотите получить ключ?",
                    style = Regular,
                    fontSize = FontSmall
                )
                Spacer(modifier = Modifier.padding(8.dp))
                AccentButton(
                    enabled = true, text = "Получить",
                    onClick = { onApprove(); onDismiss() })
                Spacer(modifier = Modifier.padding(8.dp))
                TomatoAccentButton(
                    enabled = true,
                    text = "Отклонить",
                    onClick = { onReject(); onDismiss() })
            }
        }
    }
}

