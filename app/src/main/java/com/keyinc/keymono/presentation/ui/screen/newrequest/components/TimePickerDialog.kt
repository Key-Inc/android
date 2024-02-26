package com.keyinc.keymono.presentation.ui.screen.newrequest.components

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
    minAvailableTime: LocalTime,
    maxAvailableTime: LocalTime,
    initiallySelectedTime: LocalTime,
    onClose: () -> Unit,
    onTimeChoice: (LocalTime) -> Unit
) {
    var selectedTime by remember { mutableStateOf(initiallySelectedTime) }

    TimePickerTheme {
        ClockDialog(
            state = rememberUseCaseState(
                visible = true,
                onCloseRequest = { onClose() }
            ),
            selection = ClockSelection.HoursMinutes { hours, minutes ->
                selectedTime = LocalTime.of(hours, minutes)
                onTimeChoice(selectedTime)
            },
            config = ClockConfig(
                boundary = minAvailableTime..maxAvailableTime,
                defaultTime = selectedTime,
                is24HourFormat = true
            )
        )
    }
}