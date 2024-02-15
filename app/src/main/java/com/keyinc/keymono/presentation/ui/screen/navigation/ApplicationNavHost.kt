package com.keyinc.keymono.presentation.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keyinc.keymono.presentation.ui.screen.onboarding.OnBoardingScreen
import com.keyinc.keymono.presentation.ui.screen.registration.FirstRegistrationScreen
import com.keyinc.keymono.presentation.ui.screen.splash.SplashScreen
import com.keyinc.keymono.presentation.viewModel.RegistrationViewModel

@Composable
fun ApplicationNavHost(registrationViewModel: RegistrationViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SplashScreen.route) {
        composable(Routes.SplashScreen.route) {
            SplashScreen(onNavigateToOnBoarding = {
                navController.popBackStack()
                navController.navigate(Routes.OnBoardingScreen.route) {
                    launchSingleTop = true
                }
            })
        }
        composable(Routes.FirstRegistrationScreen.route) {
            FirstRegistrationScreen(registrationViewModel = registrationViewModel)
        }
        composable(Routes.OnBoardingScreen.route) {
            OnBoardingScreen(
                onNavigateToRegistration = {
                    navController.navigate(Routes.FirstRegistrationScreen.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }

}