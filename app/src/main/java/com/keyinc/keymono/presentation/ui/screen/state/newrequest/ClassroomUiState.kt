package com.keyinc.keymono.presentation.ui.screen.state.newrequest

import com.keyinc.keymono.domain.entity.Classroom

sealed class ClassroomUiState {
    data object Initial : ClassroomUiState()
    data object Loading : ClassroomUiState()
    data class Error(val errorMessage: String? = null) : ClassroomUiState()
    data class Content(val classrooms: List<Classroom>) : ClassroomUiState()
}