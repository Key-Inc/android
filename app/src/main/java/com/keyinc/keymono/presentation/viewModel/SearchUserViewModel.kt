package com.keyinc.keymono.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.keymono.domain.usecase.key.GetUserListUseCase
import com.keyinc.keymono.domain.usecase.key.TransferKeyForUserUseCase
import com.keyinc.keymono.presentation.ui.errorHandler.RequestExceptionHandler
import com.keyinc.keymono.presentation.ui.screen.state.SearchUserState
import com.keyinc.keymono.presentation.ui.screen.state.UiSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchUserViewModel @Inject constructor(
    private val getUserUseCase: GetUserListUseCase,
    private val transferKeyForUserUseCase: TransferKeyForUserUseCase
) : ViewModel() {

    private val _searchUserState = MutableStateFlow<SearchUserState>(SearchUserState.Initial)
    val searchUserState = _searchUserState.asStateFlow()

    private val _uiState = MutableStateFlow(UiSearchState(""))
    val uiState = _uiState.asStateFlow()


    private val exceptionHandler = RequestExceptionHandler(
        onUnauthorizedException = {
            _searchUserState.value = SearchUserState.Unauthorized
        },
        onBaseException = {
            _searchUserState.value = SearchUserState.Error("Неизвестная ошибка")
        },
        onBadRegistrationRequest = {
            _searchUserState.value =
                SearchUserState.Error("Неизвестная ошибка")
        }
    )

    fun onUserNameChanged(name: String) {
        _uiState.value = UiSearchState(name)
        getUserByName(name)
    }

    fun transferKeyForUser(id: String, userId: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler.coroutineExceptionHandler) {
            transferKeyForUserUseCase.invoke(id, userId)
        }
    }


    fun getUserByName(name: String) {
        _searchUserState.value = SearchUserState.Loading

        viewModelScope.launch(Dispatchers.IO + exceptionHandler.coroutineExceptionHandler) {
            _searchUserState.value = SearchUserState.Content(getUserUseCase.invoke(name))
        }
    }

}