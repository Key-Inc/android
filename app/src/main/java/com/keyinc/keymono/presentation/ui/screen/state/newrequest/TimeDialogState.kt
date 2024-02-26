package com.keyinc.keymono.presentation.ui.screen.state.newrequest

sealed class TimeDialogState {
    data object DialogHidden : TimeDialogState()
    data object PickingStartTime : TimeDialogState()
    data object PickingEndTime : TimeDialogState()
}