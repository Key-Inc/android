package com.keyinc.keymono.presentation.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.keyinc.keymono.presentation.ui.theme.DatePickerTheme
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeConfig
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import java.time.LocalDate


@Composable
fun DatePicker(onCloseSelection: () -> Unit, selectedDate: MutableState<LocalDate?>) {
    DatePickerTheme() {
        DateTimeDialog(
            state = rememberUseCaseState(visible = true, onCloseRequest = { onCloseSelection() }),
            selection = DateTimeSelection.Date { newDate ->
                selectedDate.value = newDate
            },

            config = DateTimeConfig(
                maxYear = LocalDate.now().year,
                minYear = LocalDate.now().year - 100
            )

        )
    }
}


