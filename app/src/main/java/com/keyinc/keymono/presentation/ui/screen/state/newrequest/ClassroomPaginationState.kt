package com.keyinc.keymono.presentation.ui.screen.state.newrequest

import com.keyinc.keymono.domain.entity.Classroom

data class ClassroomPaginationState(
    val isLoading: Boolean = false,
    val items: List<Classroom> = emptyList(),
    val errorMessage: String? = null,
    val isEndReached: Boolean = false,
    val currentPage: Int = 1
)
