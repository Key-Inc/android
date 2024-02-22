package com.keyinc.keymono.presentation.ui.screen.newrequest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.component.AccentButton
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.ClassroomField
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.IsRecurringField
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.TimeField
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.TimePickerDialog
import com.keyinc.keymono.presentation.ui.theme.Padding64
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import java.time.LocalTime

@Composable
fun SendRequestScreen(
    classroomNumber: Int,
    startTime: LocalTime,
    endTime: LocalTime,
    isRecurring: Boolean,
    modifier: Modifier = Modifier
) {
    // TODO add sealed class with states of DialogHidden, PickingStartTime, PickingEndTime
    var isTimePickerShown by remember { mutableStateOf(false) }
    var isStartTimePicking by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = PaddingMedium),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Spacer(modifier = Modifier.height(PaddingLarge))
        ClassroomField(classroomNumber = classroomNumber)
        TimeField(
            isStartingTime = true,
            time = startTime.toString(),
            onEditClick = {
                // TODO change sealed class state in vm
                isTimePickerShown = !isTimePickerShown
                isStartTimePicking = true
            }
        )
        TimeField(
            isStartingTime = false,
            time = endTime.toString(),
            onEditClick = {
                isTimePickerShown = !isTimePickerShown
                isStartTimePicking = false
            }
        )
        IsRecurringField(isRecurring = isRecurring)
        Spacer(modifier = Modifier.weight(1f))
        AccentButton(
            modifier = Modifier.padding(
                bottom = Padding64,
                start = PaddingLarge,
                end = PaddingLarge
            ),
            text = stringResource(id = R.string.send_a_request),
            onClick = { /* TODO navigate */ }
        )

        if (!isTimePickerShown) return
        TimePickerDialog(
            isStartTimePicking = isStartTimePicking,
            startTime = startTime,
            endTime = endTime,
            onClose = {
                isTimePickerShown = !isTimePickerShown
            }
        )
    }
}

@Preview
@Composable
fun SendRequestScreenPreview() {
    SendRequestScreen(
        classroomNumber = 220,
        startTime = LocalTime.of(8, 45),
        endTime = LocalTime.of(14, 45),
        isRecurring = true
    )
}