package com.keyinc.keymono.presentation.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavController.navigateBackOrToAvoidingBackStack(backStackRoute: String) {
    if (previousBackStackEntry?.destination?.route == backStackRoute)
        popBackStack()
    else
        navigate(backStackRoute)
}

fun NavOptionsBuilder.clearAllBackStack(navController: NavController) {
    popUpTo(
        navController.graph.id
    ) {
        inclusive = true
        saveState = true
    }
}