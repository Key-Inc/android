package com.keyinc.keymono.presentation.ui.screen.newrequest

import android.util.Log
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.ScheduleElement
import com.keyinc.keymono.presentation.ui.screen.state.newrequest.ScheduleUiState
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.CalendarDate
import com.keyinc.keymono.presentation.ui.theme.CalendarDayOfWeek
import com.keyinc.keymono.presentation.ui.theme.LightGray
import com.keyinc.keymono.presentation.ui.theme.Padding24
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import com.keyinc.keymono.presentation.ui.theme.PaddingSmall
import com.keyinc.keymono.presentation.ui.util.displayText
import com.keyinc.keymono.presentation.ui.util.formatCalendarDay
import com.keyinc.keymono.presentation.viewModel.NewRequestViewModel
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import java.time.LocalDate

@Composable
fun DateTimeChoiceScreen(
    modifier: Modifier = Modifier,
    viewModel: NewRequestViewModel,
    onNavigateToSendRequest: () -> Unit
) {
    val scheduleUiState by viewModel.scheduleUiState.collectAsStateWithLifecycle()
    val calendarState by viewModel.calendarState.collectAsStateWithLifecycle()

    val state = rememberWeekCalendarState(
        startDate = calendarState.startDate,
        endDate = calendarState.endDate,
        firstVisibleWeekDate = calendarState.startDate,
        firstDayOfWeek = calendarState.startDate.dayOfWeek
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
                        isSelected = calendarState.selectedDate == day.date,
                        onClick = viewModel::onDateChoice
                    )
                }
            )
            Spacer(modifier = Modifier.height(Padding24))
        }

        when (scheduleUiState) {
            ScheduleUiState.Initial -> Unit
            is ScheduleUiState.Content -> {
                val schedule = (scheduleUiState as ScheduleUiState.Content).schedule
                items(schedule) { scheduleElement ->
                    ScheduleElement(
                        modifier = Modifier.padding(horizontal = PaddingMedium),
                        scheduleElement = scheduleElement,
                        onScheduleElementClick = {
                            viewModel.onTimeRangeChoice(it)
                            onNavigateToSendRequest()
                        }
                    )
                    Spacer(modifier = Modifier.height(PaddingSmall))
                }
            }
            ScheduleUiState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
            is ScheduleUiState.Error -> {
                // TODO
                Log.e("DateTimeChoiceScreen", (scheduleUiState as ScheduleUiState.Error).errorMessage ?: "error")
            }
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