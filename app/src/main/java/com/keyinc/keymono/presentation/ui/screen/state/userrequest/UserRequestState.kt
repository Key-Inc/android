package com.keyinc.keymono.presentation.ui.screen.state.userrequest

import com.keyinc.keymono.domain.entity.RequestDTO

sealed class UserRequestState {
    data object Initial : UserRequestState()
    data object Loading : UserRequestState()
    data class Content(var requestList: List<RequestDTO>) : UserRequestState()
    data object NoRequest : UserRequestState()
    data object Unauthorized : UserRequestState()
    data object Error : UserRequestState()
}