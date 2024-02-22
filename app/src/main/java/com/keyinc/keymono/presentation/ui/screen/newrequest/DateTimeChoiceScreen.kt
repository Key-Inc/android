package com.keyinc.keymono.presentation.ui.screen.newrequest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.data.MockSchedule.schedule
import com.keyinc.keymono.presentation.model.ScheduleElement
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.ScheduleElement
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.CalendarDate
import com.keyinc.keymono.presentation.ui.theme.CalendarDayOfWeek
import com.keyinc.keymono.presentation.ui.theme.LightGray
import com.keyinc.keymono.presentation.ui.theme.Padding24
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import com.keyinc.keymono.presentation.ui.theme.PaddingSmall
import com.keyinc.keymono.presentation.ui.util.displayText
import com.keyinc.keymono.presentation.ui.util.formatCalendarDay
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateTimeChoiceScreen(
    modifier: Modifier = Modifier,
    schedule: List<ScheduleElement>
) {
    val startDate = remember { LocalDate.now() }
    val endDate = remember { startDate.plusDays(6) }
    val firstDayOfWeek = remember { startDate.dayOfWeek }
    var selection by remember { mutableStateOf(startDate) }

    val state = rememberWeekCalendarState(
        startDate = startDate,
        endDate = endDate,
        firstVisibleWeekDate = startDate,
        firstDayOfWeek = firstDayOfWeek
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            WeekCalendar(
                modifier = Modifier.background(LightGray),
                state = state,
                dayContent = { day ->
                    Day(
                        date = day.date,
                        isSelected = selection == day.date,
                        onClick = { selection = it }
                    )
                }
            )
            Spacer(modifier = Modifier.height(Padding24))
        }

        items(schedule) { scheduleElement ->
            ScheduleElement(
                modifier = Modifier.padding(horizontal = PaddingMedium),
                scheduleElement = scheduleElement
            )
            Spacer(modifier = Modifier.height(PaddingSmall))
        }
    }
}

@Composable
private fun Day(
    date: LocalDate,
    isSelected: Boolean,
    onClick: (LocalDate) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(date) },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(vertical = PaddingMedium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = date.dayOfWeek.displayText(),
                color = Color.Black,
                style = CalendarDayOfWeek
            )
            Text(
                text = formatCalendarDay(date),
                color = Color.Black,
                style = CalendarDate
            )
        }
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(Accent)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview
@Composable
fun DateTimeChoiceScreenPreview() {
    val formatter = DateTimeFormatter.ofPattern("H:mm")
    val scheduleElementsDto = schedule
    val scheduleElements = scheduleElementsDto.map {
        ScheduleElement(
            startTime = it.startDate.format(formatter),
            endTime = it.endDate.format(formatter),
            status = it.status
        )
    }

    DateTimeChoiceScreen(schedule = scheduleElements)
}