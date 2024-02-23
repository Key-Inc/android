package com.keyinc.keymono.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.keymono.data.paginator.PaginatorImpl
import com.keyinc.keymono.domain.usecase.classroom.GetClassroomsUseCase
import com.keyinc.keymono.presentation.ui.screen.state.newrequest.ClassroomPaginationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewRequestViewModel @Inject constructor(
    private val getClassroomsUseCase: GetClassroomsUseCase
) : ViewModel() {

    private val _classroomPaginationState = MutableStateFlow(ClassroomPaginationState())
    val classroomPaginationState: StateFlow<ClassroomPaginationState>
        get() = _classroomPaginationState.asStateFlow()

    private val paginator = PaginatorImpl(
        initialKey = _classroomPaginationState.value.currentPage,
        onLoadUpdated = {
            _classroomPaginationState.value = _classroomPaginationState.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            getClassroomsUseCase(
                page = nextPage,
                size = 10
            )
        },
        getNextKey = {
            _classroomPaginationState.value.currentPage + 1
        },
        onError = {
            _classroomPaginationState.value = _classroomPaginationState.value.copy(errorMessage = it?.message)
        },
        onSuccess = { newItems, newPage ->
            _classroomPaginationState.value = _classroomPaginationState.value.copy(
                items = _classroomPaginationState.value.items + newItems,
                currentPage = newPage,
                isEndReached = newItems.isEmpty()
            )
        }
    )

    init {
        loadNextClassrooms()
    }

    fun loadNextClassrooms() {
        Log.d(TAG, "loaded")
        viewModelScope.launch(Dispatchers.IO) {
            paginator.loadNextItems()
        }
    }

    private companion object {
        const val TAG = "NewRequestViewModel"
    }

}