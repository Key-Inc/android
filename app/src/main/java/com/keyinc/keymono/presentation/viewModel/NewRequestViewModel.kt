package com.keyinc.keymono.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.keymono.data.paginator.PaginatorImpl
import com.keyinc.keymono.domain.entity.Classroom
import com.keyinc.keymono.domain.entity.KeyRequestCreateDto
import com.keyinc.keymono.domain.usecase.account.GetUserRoleUseCase
import com.keyinc.keymono.domain.usecase.classroom.GetClassroomsUseCase
import com.keyinc.keymono.domain.usecase.request.CreateNewKeyRequestUseCase
import com.keyinc.keymono.domain.usecase.request.GetScheduleUseCase
import com.keyinc.keymono.presentation.model.ScheduleElement
import com.keyinc.keymono.presentation.ui.screen.state.newrequest.CalendarState
import com.keyinc.keymono.presentation.ui.screen.state.newrequest.ClassroomPaginationState
import com.keyinc.keymono.presentation.ui.screen.state.newrequest.NewRequestState
import com.keyinc.keymono.presentation.ui.screen.state.newrequest.NewRequestUiState
import com.keyinc.keymono.presentation.ui.screen.state.newrequest.ScheduleUiState
import com.keyinc.keymono.presentation.ui.screen.state.newrequest.TimeDialogState
import com.keyinc.keymono.presentation.ui.util.DateConverterUtil
import com.keyinc.keymono.presentation.ui.util.DateConverterUtil.changeTimeInLocalDateTime
import com.keyinc.keymono.presentation.ui.util.DateConverterUtil.convertTimeToHoursAndMinutes
import com.keyinc.keymono.presentation.ui.util.DateConverterUtil.joinLocalDateAndStringTime
import com.keyinc.keymono.presentation.ui.util.DateConverterUtil.toServerLocalDateTime
import com.keyinc.keymono.presentation.ui.util.NetworkErrorCodes
import com.keyinc.keymono.presentation.ui.util.PresentationConstants.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NewRequestViewModel @Inject constructor(
    private val getClassroomsUseCase: GetClassroomsUseCase,
    private val getScheduleUseCase: GetScheduleUseCase,
    private val createNewKeyRequestUseCase: CreateNewKeyRequestUseCase,
    private val getUserRoleUseCase: GetUserRoleUseCase
) : ViewModel() {


    var scheduleElementMinTime: LocalTime? = null
        private set
    var scheduleElementMaxTime: LocalTime? = null
        private set

    private val _timeDialogState = MutableStateFlow<TimeDialogState>(TimeDialogState.DialogHidden)
    val timeDialogState: StateFlow<TimeDialogState>
        get() = _timeDialogState

    private val _newRequestState = MutableStateFlow(NewRequestState())
    val newRequestState: StateFlow<NewRequestState>
        get() = _newRequestState.asStateFlow()

    private val _newRequestUiState = MutableStateFlow<NewRequestUiState>(NewRequestUiState.Initial)
    val newRequestUiState: StateFlow<NewRequestUiState>
        get() = _newRequestUiState.asStateFlow()

    private val _scheduleUiState = MutableStateFlow<ScheduleUiState>(ScheduleUiState.Initial)
    val scheduleUiState: StateFlow<ScheduleUiState>
        get() = _scheduleUiState.asStateFlow()

    private val _calendarState = MutableStateFlow(CalendarState())
    val calendarState: StateFlow<CalendarState>
        get() = _calendarState.asStateFlow()

    private val _classroomPaginationState = MutableStateFlow(ClassroomPaginationState())
    val classroomPaginationState: StateFlow<ClassroomPaginationState>
        get() = _classroomPaginationState.asStateFlow()

    private val scheduleExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                NetworkErrorCodes.UNAUTHORIZED -> _scheduleUiState.value = ScheduleUiState.Error(exception.message ?: "Unknown exception")
                else -> _scheduleUiState.value = ScheduleUiState.Error(exception.message ?: "Unknown exception")
            }
            else -> _scheduleUiState.value = ScheduleUiState.Error(exception.message ?: "Unknown exception")
        }
    }

    private val _isRecurringAllowed = mutableStateOf(false)
    val isRecurringAllowed: State<Boolean>
        get() = _isRecurringAllowed

    // TODO create parametrized exceptionHandler?
    private val newRequestExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                NetworkErrorCodes.UNAUTHORIZED -> _newRequestUiState.value = NewRequestUiState.Error(exception.message ?: "Unknown exception")
                else -> _newRequestUiState.value = NewRequestUiState.Error(exception.message ?: "Unknown exception")
            }
            else -> _newRequestUiState.value = NewRequestUiState.Error(exception.message ?: "Unknown exception")
        }
    }

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
        // TODO use and inject in nested navhost
//        val userRole = getUserRole()
//        _isRecurringAllowed.value = userRole != "Student"
    }

    fun loadNextClassrooms() {
        Log.d(TAG, "loaded")
        viewModelScope.launch(Dispatchers.IO) {
            paginator.loadNextItems()
        }
    }

//    private fun getUserRole(): String {
//        var userRole = EMPTY_STRING
//        viewModelScope.launch(Dispatchers.IO) {
//            userRole = getUserRoleUseCase.execute()
//        }
//        return userRole
//    }

    private fun loadSchedule() {
        _scheduleUiState.value = ScheduleUiState.Loading
        viewModelScope.launch(Dispatchers.IO + scheduleExceptionHandler) {
            val selectedDate = _calendarState.value.selectedDate

            val scheduleResponse = getScheduleUseCase(
                classroomId = _newRequestState.value.classroomId ?: ERROR_UUID,
                date = selectedDate
            )
            val schedule = scheduleResponse.map {
                ScheduleElement(
                    startTimeDisplay = convertTimeToHoursAndMinutes(it.startDate),
                    startDateTime = joinLocalDateAndStringTime(selectedDate, it.startDate),
                    endTimeDisplay = convertTimeToHoursAndMinutes(it.endDate),
                    endDateTime = joinLocalDateAndStringTime(selectedDate, it.endDate),
                    status = it.status
                )
            }

            _scheduleUiState.value = ScheduleUiState.Content(
                schedule = schedule
            )
        }
    }

    fun onClassroomChoice(classroom: Classroom) {
        _newRequestState.value = _newRequestState.value.copy(
            classroomId = classroom.id,
            classroomNumber = classroom.number
        )
        loadSchedule()
    }

    fun onDateChoice(date: LocalDate) {
        _calendarState.value = _calendarState.value.copy(
            selectedDate = date
        )
        loadSchedule()
    }

    fun onTimeRangeChoice(scheduleElement: ScheduleElement) {
        _newRequestState.value = _newRequestState.value.copy(
            startDate = scheduleElement.startDateTime,
            endDate = scheduleElement.endDateTime
        )
        scheduleElementMinTime = scheduleElement.startDateTime.toLocalTime()
        scheduleElementMaxTime = scheduleElement.endDateTime.toLocalTime()
    }

    fun onStartTimeChoice(time: LocalTime) {
        val startDate = _newRequestState.value.startDate ?: return

        _newRequestState.value = _newRequestState.value.copy(
            startDate = changeTimeInLocalDateTime(startDate, time),
        )
    }

    fun onEndTimeChoice(time: LocalTime) {
        val endDate = _newRequestState.value.endDate ?: return

        _newRequestState.value = _newRequestState.value.copy(
            endDate = changeTimeInLocalDateTime(endDate, time),
        )
    }

    fun onEndDateOfRecurrenceChoice(date: String) {
        _newRequestState.value = _newRequestState.value.copy(
            endDateOfRecurrence = date
        )
    }

    fun onCancelRecurrence() {
        _newRequestState.value = _newRequestState.value.copy(
            endDateOfRecurrence = null
        )
    }

    fun onCloseTimeDialog() {
        _timeDialogState.value = TimeDialogState.DialogHidden
    }

    fun onPickingStartTime() {
        _timeDialogState.value = TimeDialogState.PickingStartTime
    }

    fun onPickingEndTime() {
        _timeDialogState.value = TimeDialogState.PickingEndTime
    }

    fun onCreateNewKeyRequest() {
        _newRequestUiState.value = NewRequestUiState.Loading
        viewModelScope.launch(Dispatchers.IO + newRequestExceptionHandler) {
            var endDateOfRecurrence: String? = null
            if (_isRecurringAllowed.value) {
                endDateOfRecurrence = DateConverterUtil.convertDateToServerFormat(_newRequestState.value.endDateOfRecurrence ?: EMPTY_STRING)
            }
            createNewKeyRequestUseCase(
                KeyRequestCreateDto(
                    startDate = toServerLocalDateTime(_newRequestState.value.startDate ?: ERROR_LOCAL_DATE_TIME),
                    endDate = toServerLocalDateTime(_newRequestState.value.endDate ?: ERROR_LOCAL_DATE_TIME),
                    endDateOfRecurrence = endDateOfRecurrence,
                    classroomId = _newRequestState.value.classroomId.toString()
                )
            )
            _newRequestUiState.value = NewRequestUiState.Success
        }
    }

    private companion object {
        const val TAG = "NewRequestViewModel"
        val ERROR_UUID: UUID = UUID(0L, 0L)
        val ERROR_LOCAL_DATE_TIME: LocalDateTime = LocalDateTime.MIN
    }

}