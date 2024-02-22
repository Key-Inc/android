package com.keyinc.keymono.presentation.ui.screen.newrequest.components

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.keyinc.keymono.presentation.ui.theme.TimePickerTheme
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    isStartTimePicking: Boolean,
    startTime: LocalTime,
    endTime: LocalTime,
    onClose: () -> Unit
) {
    var selectedTime by remember { mutableStateOf(startTime) }

    TimePickerTheme {
        ClockDialog(
            state = rememberUseCaseState(
                visible = true,
                onCloseRequest = { onClose() }
            ),
            selection = ClockSelection.HoursMinutes { hours, minutes ->
                selectedTime = LocalTime.of(hours, minutes)

                if (isStartTimePicking) {
                    // TODO change vm state
                    Log.d("TimePickerDialog", "Changed the start time")
                } else {
                    // TODO change vm state
                    Log.d("TimePickerDialog", "Changed the end time")
                }
            },
            config = ClockConfig(
                boundary = startTime..endTime,
                defaultTime = selectedTime,
                is24HourFormat = true
            )
        )
    }
}