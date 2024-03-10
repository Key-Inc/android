package com.keyinc.keymono.presentation.ui.navigation

sealed class Routes(val route: String) {
    data object SplashScreen : Routes("splash_screen")
    data object OnBoardingScreen : Routes("on_boarding_screen")
    data object LoginScreen : Routes("login_screen")
    data object FirstRegistrationScreen : Routes("first_registration_screen")
    data object SecondRegistrationScreen : Routes("second_registration_screen")
    data object RequestWaitingScreen : Routes("request_waiting_screen")
    data object ClassroomChoiceScreen : Routes("classroom_choice_screen")
    data object DateTimeChoiceScreen : Routes("date_time_choice_screen")
    data object SendRequestScreen : Routes("send_request_screen")
    data object ProfileScreen : Routes("profile_screen")
    data object EditProfileScreen : Routes("edit_profile_screen")
}