package com.keyinc.keymono.presentation.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.BottomNavigationBackground
import com.keyinc.keymono.presentation.ui.theme.BottomNavigationContent
import com.keyinc.keymono.presentation.viewModel.NewRequestViewModel
import com.keyinc.keymono.presentation.viewModel.ProfileViewModel

@Composable
fun BottomNavigationWrapper(
    rootNavController: NavHostController,
    onCloseApp: () -> Unit,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    newRequestViewModel: NewRequestViewModel = hiltViewModel()
) {
    val bottomNavigationNavController = rememberNavController()

    BackHandler {
        if (bottomNavigationNavController.previousBackStackEntry == null) onCloseApp()
        else bottomNavigationNavController.popBackStack()
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                buttons = BottomNavItems.items,
                navController = bottomNavigationNavController,
                onItemClick = {
                    bottomNavigationNavController.navigate(it.route)
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            BottomNavigation(
                bottomNavigationNavController = bottomNavigationNavController,
                rootNavController = rootNavController,
                newRequestViewModel = newRequestViewModel,
                profileViewModel = profileViewModel
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    buttons: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = modifier,
        containerColor = BottomNavigationBackground,
        contentColor = BottomNavigationContent
    ) {
        buttons.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onItemClick(item)
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = ImageVector.vectorResource(id = item.iconId),
                        contentDescription = stringResource(id = item.labelId),
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Accent,
                    selectedTextColor = Accent,
                    indicatorColor = BottomNavigationBackground,
                    unselectedIconColor = BottomNavigationContent,
                    unselectedTextColor = BottomNavigationContent
                )
            )
        }
    }
}