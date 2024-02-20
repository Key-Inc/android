package com.keyinc.keymono.presentation.ui.screen.newrequest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.component.AccentButton
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.ClassroomField
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.IsRecurringField
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.TimeField
import com.keyinc.keymono.presentation.ui.theme.Padding128
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium

@Composable
fun SendRequestScreen(
    classroomNumber: Int,
    startTime: String,
    endTime: String,
    isRecurring: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = PaddingMedium),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Spacer(modifier = Modifier.height(PaddingLarge))
        ClassroomField(classroomNumber = classroomNumber)
        TimeField(isStartingTime = true, time = startTime)
        TimeField(isStartingTime = false, time = endTime)
        IsRecurringField(isRecurring = isRecurring)
        Spacer(modifier = Modifier.weight(1f))
        AccentButton(
            modifier = Modifier.padding(
                bottom = Padding128,
                start = PaddingLarge,
                end = PaddingLarge
            ),
            text = stringResource(id = R.string.send_a_request),
            onClick = { /* TODO navigate */ }
        )
    }
}

@Preview
@Composable
fun SendRequestScreenPreview() {
    SendRequestScreen(
        classroomNumber = 220,
        startTime = "8:45",
        endTime = "14:45",
        isRecurring = true
    )
}