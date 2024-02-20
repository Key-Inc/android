package com.keyinc.keymono.presentation.ui.navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keyinc.keymono.presentation.ui.screen.onboarding.OnBoardingScreen
import com.keyinc.keymono.presentation.ui.screen.registration.FirstRegistrationScreen
import com.keyinc.keymono.presentation.ui.screen.registration.SecondRegistrationScreen
import com.keyinc.keymono.presentation.ui.screen.request.RequestWaitingScreen
import com.keyinc.keymono.presentation.ui.screen.splash.SplashScreen
import com.keyinc.keymono.presentation.viewModel.RegistrationViewModel

@Composable
fun ApplicationNavHost(
    registrationViewModel: RegistrationViewModel,
    navController: NavHostController = rememberNavController()
) {


    NavHost(navController = navController, startDestination = Routes.SplashScreen.route) {
        composable(Routes.SplashScreen.route) {
            SplashScreen(
                onNavigateToOnBoarding = { route ->
                    navController.popBackStack()
                    navController.navigate(route)
                },
                splashViewModel = hiltViewModel()
            )
        }

        composable(Routes.FirstRegistrationScreen.route) {
            FirstRegistrationScreen(
                registrationViewModel = registrationViewModel,
                onNavigateToBack = {
                    navController.popBackStack()
                },
                onNavigateToSecondPart = {
                    navController.navigate(Routes.SecondRegistrationScreen.route)
                }
            )
        }

        composable(Routes.OnBoardingScreen.route) {
            OnBoardingScreen(
                onNavigateToRegistration = {
                    navController.navigate(Routes.FirstRegistrationScreen.route)
                }
            )
        }

        composable(Routes.RequestWaitingScreen.route) {
            RequestWaitingScreen()
        }

        composable(Routes.SecondRegistrationScreen.route) {
            SecondRegistrationScreen(
                registrationViewModel = registrationViewModel,
                onNavigateToBack = {
                    navController.popBackStack()
                },
                onNavigateToRequestWaiting = {
                    navController.navigate(Routes.RequestWaitingScreen.route) {
                        clearAllBackStack(navController = navController)
                    }
                }
            )
        }
    }
}


fun NavOptionsBuilder.clearAllBackStack(navController: NavController) {
    popUpTo(
        navController.graph.id
    ) {
        inclusive = true
        saveState = true
    }
}