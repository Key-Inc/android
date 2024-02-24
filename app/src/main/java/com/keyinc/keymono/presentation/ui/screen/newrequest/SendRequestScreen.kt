package com.keyinc.keymono.presentation.ui.screen.newrequest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.keyinc.keymono.presentation.ui.screen.state.newrequest.TimeDialogState
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
    val timeDialogState by viewModel.timeDialogState.collectAsStateWithLifecycle()

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
            onEditClick = viewModel::onPickingStartTime
        )
        TimeField(
            isStartingTime = false,
            time = newRequestState.endDate?.toLocalTime().toString(),
            onEditClick = viewModel::onPickingEndTime
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

        if (timeDialogState is TimeDialogState.DialogHidden) return

        viewModel.scheduleElementMinTime?.let { minTime ->
            viewModel.scheduleElementMaxTime?.let { maxTime ->
                when (timeDialogState) {
                    TimeDialogState.DialogHidden -> return
                    TimeDialogState.PickingStartTime -> {
                        TimePickerDialog(
                            minAvailableTime = minTime,
                            maxAvailableTime = maxTime,
                            initiallySelectedTime = maxTime,
                            onClose = viewModel::onCloseTimeDialog,
                            onTimeChoice = viewModel::onStartTimeChoice
                        )
                    }
                    TimeDialogState.PickingEndTime -> {
                        TimePickerDialog(
                            minAvailableTime = minTime,
                            maxAvailableTime = maxTime,
                            initiallySelectedTime = minTime,
                            onClose = viewModel::onCloseTimeDialog,
                            onTimeChoice = viewModel::onEndTimeChoice
                        )
                    }
                }
            }
        }
    }
}