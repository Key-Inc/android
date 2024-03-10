package com.keyinc.keymono.presentation.ui.screen.newrequest

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.component.AccentButton
import com.keyinc.keymono.presentation.ui.component.DatePicker
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.ClassroomField
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.EndOfRecurrenceDateField
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.TimeField
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.TimePickerDialog
import com.keyinc.keymono.presentation.ui.screen.state.newrequest.NewRequestUiState
import com.keyinc.keymono.presentation.ui.screen.state.newrequest.TimeDialogState
import com.keyinc.keymono.presentation.ui.theme.Padding64
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import com.keyinc.keymono.presentation.viewModel.NewRequestViewModel
import java.time.LocalDate

@Composable
fun SendRequestScreen(
    viewModel: NewRequestViewModel,
    modifier: Modifier = Modifier
) {
    val newRequestUiState by viewModel.newRequestUiState.collectAsStateWithLifecycle()
    val newRequestState by viewModel.newRequestState.collectAsStateWithLifecycle()
    val timeDialogState by viewModel.timeDialogState.collectAsStateWithLifecycle()
    var isDatePickerOpened by remember { mutableStateOf(false) }
    val isRecurrenceAllowed by viewModel.isRecurringAllowed

    when (newRequestUiState) {
        NewRequestUiState.Initial -> Unit
        NewRequestUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        NewRequestUiState.Success -> {
            Toast.makeText(
                LocalContext.current,
                stringResource(id = R.string.request_successfully_created),
                Toast.LENGTH_SHORT
            ).show()
            // TODO remove toast (or add one-time event) and navigate to main screen
        }

        is NewRequestUiState.Error -> {
            // TODO
            Log.e(
                "SendRequestScreen",
                (newRequestUiState as NewRequestUiState.Error).errorMessage ?: "error"
            )
        }
    }

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
        if (isRecurrenceAllowed) {
            EndOfRecurrenceDateField(
                isRecurring = newRequestState.isRecurring,
                onChangeRecurring = {
                    if (!newRequestState.isRecurring) {
                        isDatePickerOpened = true
                    } else {
                        viewModel.onCancelRecurrence()
                    }
                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        AccentButton(
            modifier = Modifier.padding(
                bottom = Padding64,
                start = PaddingLarge,
                end = PaddingLarge
            ),
            text = stringResource(id = R.string.send_a_request),
            onClick = viewModel::onCreateNewKeyRequest
        )

        if (timeDialogState !is TimeDialogState.DialogHidden) {
            viewModel.scheduleElementMinTime?.let { minTime ->
                viewModel.scheduleElementMaxTime?.let { maxTime ->
                    when (timeDialogState) {
                        TimeDialogState.DialogHidden -> return
                        TimeDialogState.PickingStartTime -> {
                            TimePickerDialog(
                                minAvailableTime = minTime,
                                maxAvailableTime = maxTime,
                                initiallySelectedTime = minTime,
                                onClose = viewModel::onCloseTimeDialog,
                                onTimeChoice = viewModel::onStartTimeChoice
                            )
                        }

                        TimeDialogState.PickingEndTime -> {
                            TimePickerDialog(
                                minAvailableTime = minTime,
                                maxAvailableTime = maxTime,
                                initiallySelectedTime = maxTime,
                                onClose = viewModel::onCloseTimeDialog,
                                onTimeChoice = viewModel::onEndTimeChoice
                            )
                        }
                    }
                }
            }
        }

        if (isDatePickerOpened) {
            DatePicker(
                onCloseSelection = {
                    isDatePickerOpened = false
                },
                onDateChange = viewModel::onEndDateOfRecurrenceChoice,
                maxYear = LocalDate.now().year + 1,
                minYear = LocalDate.now().year
            )
        }
    }
}