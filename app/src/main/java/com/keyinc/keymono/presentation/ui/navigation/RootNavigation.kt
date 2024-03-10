package com.keyinc.keymono.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.keyinc.keymono.presentation.ui.screen.login.LoginScreen
import com.keyinc.keymono.presentation.ui.screen.onboarding.OnBoardingScreen
import com.keyinc.keymono.presentation.ui.screen.registration.FirstRegistrationScreen
import com.keyinc.keymono.presentation.ui.screen.registration.SecondRegistrationScreen
import com.keyinc.keymono.presentation.ui.screen.request.RequestWaitingScreen
import com.keyinc.keymono.presentation.ui.screen.splash.SplashScreen
import com.keyinc.keymono.presentation.viewModel.RegistrationViewModel

@Composable
fun RootNavigation(
    navController: NavHostController,
    onCloseApp: () -> Unit,
    registrationViewModel: RegistrationViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SplashScreen.route
    ) {
        composable(Routes.SplashScreen.route) {
            SplashScreen(
                onNavigateToOnBoarding = { route ->
                    navController.popBackStack()
                    navController.navigate(route)
                },
                splashViewModel = hiltViewModel()
            )
        }

        composable(Routes.OnBoardingScreen.route) {
            OnBoardingScreen(
                onNavigateToRegistration = {
                    navController.navigate(Routes.FirstRegistrationScreen.route)
                },
                onNavigateToLogin = {
                    navController.navigate(Routes.LoginScreen.route)
                }
            )
        }

        composable(Routes.LoginScreen.route) {
            LoginScreen(
                onNavigateToClassroomChoice = {
                    navController.navigate(Routes.UserRequestScreen.route)
                },
                onNavigateToRegister = {
                    navController.navigateBackOrToAvoidingBackStack(Routes.FirstRegistrationScreen.route)
                },
                onNavigateToRequest = {
                    navController.navigate(Routes.RequestWaitingScreen.route) {
                        clearAllBackStack(navController)
                    }
                }
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
                },
                onNavigateToLogin = {
                    navController.navigateBackOrToAvoidingBackStack(Routes.LoginScreen.route)
                }
            )
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
                },
                onNavigateToLogin = {
                    navController.navigate(Routes.LoginScreen.route)
                },
                onUnauthorizedError = {
                    navController.navigate(Routes.LoginScreen.route) {
                        clearAllBackStack(navController = navController)
                    }
                }
            )
        }

        composable(Routes.RequestWaitingScreen.route) {
            RequestWaitingScreen(
                onNavigateToClassroomChoice = {
                    navController.navigate(Routes.ClassroomChoiceScreen.route)
                },
                onNavigateToLogin = {
                    navController.navigate(Routes.LoginScreen.route) {
                        clearAllBackStack(navController = navController)
                    }
                }
            )
        }

        composable(Routes.UserRequestScreen.route) {
            BottomNavigationWrapper(
                rootNavController = navController,
                onCloseApp = onCloseApp
            )
        }
    }
}