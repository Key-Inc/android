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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.component.AccentButton
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.ClassroomField
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.IsRecurringField
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.TimeField
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.TimePickerDialog
import com.keyinc.keymono.presentation.ui.theme.Padding64
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import com.keyinc.keymono.presentation.viewModel.NewRequestViewModel

@Composable
fun SendRequestScreen(
    viewModel: NewRequestViewModel,
    modifier: Modifier = Modifier
) {
    val newRequestState by viewModel.newRequestState.collectAsStateWithLifecycle()
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
        ClassroomField(classroomNumber = newRequestState.classroomNumber ?: 0)
        TimeField(
            isStartingTime = true,
            time = newRequestState.startDate?.toLocalTime().toString(),
            onEditClick = {
                // TODO change sealed class state in vm
                isTimePickerShown = !isTimePickerShown
                isStartTimePicking = true
            }
        )
        TimeField(
            isStartingTime = false,
            time = newRequestState.endDate?.toLocalTime().toString(),
            onEditClick = {
                isTimePickerShown = !isTimePickerShown
                isStartTimePicking = false
            }
        )
        IsRecurringField(
            isRecurring = newRequestState.isRecurring,
            onChangeRecurring = viewModel::onChangeRecurring
        )
        Spacer(modifier = Modifier.weight(1f))
        AccentButton(
            modifier = Modifier.padding(
                bottom = Padding64,
                start = PaddingLarge,
                end = PaddingLarge
            ),
            text = stringResource(id = R.string.send_a_request),
            onClick = {
                /* TODO navigate to main screen */
            }
        )

        if (!isTimePickerShown) return
        viewModel.scheduleElementMinTime?.let { minTime ->
            viewModel.scheduleElementMaxTime?.let { maxTime ->
                if (isStartTimePicking) {
                    TimePickerDialog(
                        minAvailableTime = minTime,
                        maxAvailableTime = maxTime,
                        initiallySelectedTime = minTime,
                        onClose = {
                            isTimePickerShown = !isTimePickerShown
                        },
                        onTimeChoice = viewModel::onStartTimeChoice
                    )
                } else {
                    TimePickerDialog(
                        minAvailableTime = minTime,
                        maxAvailableTime = maxTime,
                        initiallySelectedTime = maxTime,
                        onClose = {
                            isTimePickerShown = !isTimePickerShown
                        },
                        onTimeChoice = viewModel::onEndTimeChoice
                    )
                }
            }
        }
    }
}