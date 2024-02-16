package com.keyinc.keymono.presentation.ui.screen.navigation

sealed class Routes(val route: String) {
    data object SplashScreen : Routes("splash_screen")
    data object OnBoardingScreen : Routes("on_boarding_screen")
    data object FirstRegistrationScreen : Routes("first_registration_screen")
    data object SecondRegistrationScreen : Routes("second_registration_screen")
    data object RequestWaitingScreen : Routes("request_waiting_screen")
}