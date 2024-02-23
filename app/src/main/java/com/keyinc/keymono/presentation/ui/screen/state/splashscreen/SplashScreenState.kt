package com.keyinc.keymono.presentation.ui.screen.state.splashscreen

sealed class SplashScreenState {
    data object Initial : SplashScreenState()
    data object Loading : SplashScreenState()
    data object RequestConfirmed : SplashScreenState()
    data object UserLoggedIn : SplashScreenState()
    data object UserNotLoggedIn : SplashScreenState()
    data object Idling : SplashScreenState()
    data class Error(val errorMessage: String? = null) : SplashScreenState()
}