package com.keyinc.keymono.presentation.ui.screen.state.newrequest

import com.keyinc.keymono.presentation.model.ScheduleElement

sealed class ScheduleUiState {
    data object Initial : ScheduleUiState()
    data object Loading : ScheduleUiState()
    data class Error(val errorMessage: String? = null) : ScheduleUiState()
    data class Content(val schedule: List<ScheduleElement>) : ScheduleUiState()
}