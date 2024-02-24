package com.keyinc.keymono.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyinc.keymono.domain.usecase.account.GetUserRequestStatus
import com.keyinc.keymono.domain.usecase.account.IsUserLoggedInUseCase
import com.keyinc.keymono.presentation.ui.screen.state.splashscreen.SplashScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
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


    private val _splashScreenState = MutableStateFlow<SplashScreenState>(SplashScreenState.Initial)
    val splashScreenState: StateFlow<SplashScreenState>
        get() = _splashScreenState.asStateFlow()


    fun isUserLoggedIn() {
        viewModelScope.launch {
            _splashScreenState.value = SplashScreenState.Loading
            _splashScreenState.value = if (isUserLoggedInUseCase()) {
                SplashScreenState.UserLoggedIn
            } else {
                SplashScreenState.UserNotLoggedIn
            }
        }
    }


    fun getRequestStatus() {
        _splashScreenState.value = SplashScreenState.RequestConfirmed
        // TODO uncomment
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                _splashScreenState.value = SplashScreenState.Loading
//                _splashScreenState.value = if (getRequestStatusUseCase()) {
//                    SplashScreenState.RequestConfirmed
//                } else {
//                    SplashScreenState.Idling
//                }
//            } catch (e: Exception) {
//                _splashScreenState.value = SplashScreenState.Error(e.message ?: "Unknown error")
//            }
//        }
    }
}
