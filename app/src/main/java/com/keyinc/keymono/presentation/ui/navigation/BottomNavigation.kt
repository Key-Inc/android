package com.keyinc.keymono.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.keyinc.keymono.presentation.ui.SearchUserScreen
import com.keyinc.keymono.presentation.ui.screen.UserKeysScreen
import com.keyinc.keymono.presentation.ui.screen.newrequest.ClassroomChoiceScreen
import com.keyinc.keymono.presentation.ui.screen.newrequest.DateTimeChoiceScreen
import com.keyinc.keymono.presentation.ui.screen.newrequest.SendRequestScreen
import com.keyinc.keymono.presentation.ui.screen.profile.EditProfileScreen
import com.keyinc.keymono.presentation.ui.screen.profile.ProfileScreen
import com.keyinc.keymono.presentation.ui.userRequest.UserRequestScreen
import com.keyinc.keymono.presentation.viewModel.NewRequestViewModel
import com.keyinc.keymono.presentation.viewModel.ProfileViewModel

@Composable
fun BottomNavigation(
    bottomNavigationNavController: NavHostController,
    rootNavController: NavHostController,
    newRequestViewModel: NewRequestViewModel,
    profileViewModel: ProfileViewModel
) {
    NavHost(
        navController = bottomNavigationNavController,
        startDestination = Routes.UserRequestScreen.route
    ) {
        composable(Routes.UserRequestScreen.route) {
            UserRequestScreen(
                onNavigateToLogin = {
                    rootNavController.navigate(Routes.LoginScreen.route) {
                        clearAllBackStack(navController = rootNavController)
                    }
                }
            )
        }

        composable(Routes.ClassroomChoiceScreen.route) {
            ClassroomChoiceScreen(
                viewModel = newRequestViewModel,
                onNavigateToDateTimeChoice = {
                    bottomNavigationNavController.navigate(Routes.DateTimeChoiceScreen.route)
                }
            )
        }

        composable(Routes.ProfileScreen.route) {
            ProfileScreen(
                viewModel = profileViewModel,
                onNavigateToEditProfile = {
                    bottomNavigationNavController.navigate(Routes.EditProfileScreen.route)
                }
            )
        }

        composable(Routes.DateTimeChoiceScreen.route) {
            DateTimeChoiceScreen(
                onNavigateToSendRequest = {
                    bottomNavigationNavController.navigate(Routes.SendRequestScreen.route)
                },
                viewModel = newRequestViewModel
            )
        }

        composable(Routes.SendRequestScreen.route) {
            SendRequestScreen(viewModel = newRequestViewModel)
        }

        composable(Routes.EditProfileScreen.route) {
            EditProfileScreen(
                viewModel = profileViewModel,
                onNavigateBack = {
                    bottomNavigationNavController.popBackStack()
                }
            )
        }
    }
}