package com.keyinc.keymono.presentation.ui.screen.state

import com.keyinc.keymono.domain.entity.UserPagedListDto

sealed class SearchUserState {
    data object Initial : SearchUserState()
    data object Loading : SearchUserState()
    data class Content(var userPagedListDto: UserPagedListDto) : SearchUserState()
    data object Unauthorized : SearchUserState()
    data class Error(var message: String) : SearchUserState()
}
