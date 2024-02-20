package com.keyinc.keymono.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.keymono.domain.usecase.account.GetUserRequestStatus
import com.keyinc.keymono.domain.usecase.account.IsUserLoggedInUseCase
import com.keyinc.keymono.presentation.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase,
    private val getRequestStatusUseCase: GetUserRequestStatus
) : ViewModel() {


    private val _userState = MutableStateFlow<UserState>(UserState.Initial)
    val userState: StateFlow<UserState>
        get() = _userState.asStateFlow()


    fun isUserLoggedIn() {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            _userState.value = if (isUserLoggedInUseCase()) {
                UserState.UserLoggedIn
            } else {
                UserState.UserNotLoggedIn
            }
        }
    }


    fun getRequestStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _userState.value = UserState.Loading
                _userState.value = if (getRequestStatusUseCase()) {
                    UserState.RequestConfirmed
                } else {
                    UserState.Idling
                }
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "Unknown error")
            }

        }
    }
}
