package com.keyinc.keymono.presentation.ui.screen.state.login

import com.keyinc.keymono.presentation.ui.util.PresentationConstants.EMPTY_STRING

// TODO errors
data class LoginState(
    var email: String = EMPTY_STRING,
    var password: String = EMPTY_STRING
)
