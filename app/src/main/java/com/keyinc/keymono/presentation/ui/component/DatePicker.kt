package com.keyinc.keymono.presentation.ui.component

import androidx.compose.runtime.Composable
import com.keyinc.keymono.presentation.ui.theme.DatePickerTheme
import com.keyinc.keymono.presentation.ui.util.DateConverterUtil
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeConfig
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import java.time.LocalDate


@Composable
fun DatePicker(onCloseSelection: () -> Unit, onDateChange: (String) -> Unit) {
    DatePickerTheme() {
        DateTimeDialog(
            state = rememberUseCaseState(visible = true, onCloseRequest = { onCloseSelection() }),
            selection = DateTimeSelection.Date { newDate ->
                onDateChange(DateConverterUtil.convertDateToString(newDate))
            },

            config = DateTimeConfig(
                maxYear = LocalDate.now().year,
                minYear = LocalDate.now().year - 100
            )

        )
    }
}


