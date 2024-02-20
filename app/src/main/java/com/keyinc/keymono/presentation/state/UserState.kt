package com.keyinc.keymono.presentation.state

sealed class UserState {
    data object Initial : UserState()
    data object Loading : UserState()
    data object RequestConfirmed : UserState()
    data object UserLoggedIn : UserState()
    data object UserNotLoggedIn : UserState()
    data object Idling : UserState()
    data class Error(val errorMessage: String? = null) : UserState()
}