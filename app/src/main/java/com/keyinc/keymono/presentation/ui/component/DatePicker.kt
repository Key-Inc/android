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
fun DatePicker(
    onCloseSelection: () -> Unit,
    onDateChange: (String) -> Unit,
    isInReversedFormat: Boolean = false,
    maxYear: Int,
    minYear: Int
) {
    DatePickerTheme() {
        DateTimeDialog(
            state = rememberUseCaseState(visible = true, onCloseRequest = { onCloseSelection() }),
            selection = DateTimeSelection.Date { newDate ->
                if (isInReversedFormat) {
                    onDateChange(DateConverterUtil.convertDateToStringReversed(newDate))
                } else {
                    onDateChange(DateConverterUtil.convertDateToString(newDate))
                }
            },
            config = DateTimeConfig(
                maxYear = maxYear,
                minYear = minYear
            )
        )
    }
}


