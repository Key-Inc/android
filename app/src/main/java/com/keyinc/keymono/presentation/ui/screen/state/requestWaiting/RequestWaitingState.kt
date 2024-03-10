package com.keyinc.keymono.presentation.ui.screen.state.requestWaiting

sealed class RequestWaitingState  {
    data object Initial : RequestWaitingState()
    data object Loading: RequestWaitingState()
    data object Success: RequestWaitingState()
    data object Unauthorized: RequestWaitingState()
    data class Error(val message: Int): RequestWaitingState()
    data object Accepted: RequestWaitingState()
    data object WrongRole : RequestWaitingState()
}