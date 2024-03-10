package com.keyinc.keymono.presentation.ui.screen.state.keytransfer

import com.keyinc.keymono.domain.entity.UserKeyDto

sealed class KeyTransferState {

    data object Initial : KeyTransferState()
    data object Loading : KeyTransferState()
    data class Content(var keyList: List<UserKeyDto>) : KeyTransferState()
    data object NoKeys : KeyTransferState()
    data object Unauthorized : KeyTransferState()
    data object Error : KeyTransferState()

}