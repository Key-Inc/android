package com.keyinc.keymono.presentation.ui.screen.newrequest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.R
import com.keyinc.keymono.data.MockSchedule
import com.keyinc.keymono.domain.entity.ScheduleStatus
import com.keyinc.keymono.presentation.model.ScheduleElement
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.Gray82
import com.keyinc.keymono.presentation.ui.theme.LightGray
import com.keyinc.keymono.presentation.ui.theme.Padding24
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.theme.PaddingSmall
import com.keyinc.keymono.presentation.ui.theme.Title
import com.keyinc.keymono.presentation.ui.utils.noRippleClickable
import java.time.format.DateTimeFormatter

@Composable
fun ScheduleElement(
    scheduleElement: ScheduleElement,
    modifier: Modifier = Modifier
) {
    val isAvailable = scheduleElement.status == ScheduleStatus.Available
    val backgroundColor = if (isAvailable) Accent else Gray82

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .noRippleClickable { /* TODO */ }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(84.dp)
                .clip(RoundedCornerShape(PaddingSmall))
                .background(backgroundColor)
                .align(Alignment.BottomCenter)
                .then(
                    if (isAvailable) {
                        Modifier.clickable {
                            // TODO to NewRequestScreen
                        }
                    } else {
                        Modifier
                    }
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            ScheduleElementText(
                modifier = Modifier.padding(
                    horizontal = Padding24
                ),
                isAvailable = isAvailable,
                startTime = scheduleElement.startTime,
                endTime = scheduleElement.endTime
            )
        }

        if (isAvailable) {
            Box(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(PaddingLarge)
                    .clip(CircleShape)
                    .background(LightGray)
                    .align(Alignment.TopEnd)
            ) {
                IconButton(
                    onClick = { /* TODO */ }
                ) {
                    Icon(
                        modifier = Modifier.size(Padding24),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
private fun ScheduleElementText(
    isAvailable: Boolean,
    startTime: String,
    endTime: String,
    modifier: Modifier = Modifier
) {
    val textColor = if (isAvailable) Color.White else Color.Black
    val text = stringResource(id = R.string.time_range, startTime, endTime) +
            " • " +
            if (isAvailable)
                stringResource(id = R.string.available)
            else
                stringResource(id = R.string.busy)

    Text(
        modifier = modifier,
        text = text,
        style = Title,
        color = textColor
    )
}

@Preview
@Composable
fun ScheduleElementPreview() {
    val formatter = DateTimeFormatter.ofPattern("H:mm")
    val scheduleElementDto = MockSchedule.schedule[0]
    val scheduleElement = ScheduleElement(
        startTime = scheduleElementDto.startDate.format(formatter),
        endTime = scheduleElementDto.endDate.format(formatter),
        status = scheduleElementDto.status
    )

    ScheduleElement(
        scheduleElement = scheduleElement
    )
}