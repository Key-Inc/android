package com.keyinc.keymono.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.CalendarDayOfWeek
import com.keyinc.keymono.presentation.ui.theme.CardBackGround
import com.keyinc.keymono.presentation.ui.theme.FontMedium
import com.keyinc.keymono.presentation.ui.theme.GreenStatus
import com.keyinc.keymono.presentation.ui.theme.OrangeStatus
import com.keyinc.keymono.presentation.ui.theme.Regular
import com.keyinc.keymono.presentation.ui.theme.Tomato
import com.keyinc.keymono.presentation.ui.util.DateConverterUtil.convertDateWithoutTime
import com.keyinc.keymono.presentation.ui.util.DateConverterUtil.convertToRequestDateFormat

@Composable
fun RequestCard(
    classRoomName: String = "220 (2) аудитория",
    requestType: String? = null,
    requestDate: String = "29.03.2024",
    requestTime: String = "9:45 - 20:45",
    requestStatus: String = "В рассмотрении"
) {

    val status = when (requestStatus) {
        "Accepted" -> "Принята"
        "Rejected" -> "Отклонена"
        else -> "В рассмотрении"
    }

    val color = when (requestStatus) {
        "Accepted" -> GreenStatus
        "Rejected" -> Tomato
        else -> OrangeStatus
    }

    val requestTypeParam =
        if (requestType == null) "Неповторяющаяся" else "Повторяющаяся до ${
            convertDateWithoutTime(
                requestType
            )
        }"

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.repeat_request_icon),
                    contentDescription = null
                )
                Text(text = requestTypeParam, modifier = Modifier.padding(start = 8.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.calendar_icon),
                    contentDescription = null
                )
                Text(
                    text = convertToRequestDateFormat(requestDate),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Text(
                text = requestTime,
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, bottom = 16.dp),
                style = CalendarDayOfWeek,
                color = Accent,
                fontSize = FontMedium
            )
        }
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .padding(end = 8.dp)
                .align(Alignment.TopEnd),
            colors = CardDefaults.cardColors(
                containerColor = color
            )
        ) {
            Text(text = status, modifier = Modifier.padding(8.dp), color = Color.White)
        }
    }
}

