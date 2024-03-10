package com.keyinc.keymono.presentation.ui.navigation

import com.keyinc.keymono.R

object BottomNavItems {
    val items = listOf(
        BottomNavItem(
            labelId = R.string.current_requests,
            iconId = R.drawable.ic_list,
            route = Routes.UserRequestScreen.route
        ),
        BottomNavItem(
            labelId = R.string.incoming_requests,
            iconId = R.drawable.ic_inbox,
            route = Routes.TransferRequestsScreen.route
        ),
        BottomNavItem(
            labelId = R.string.new_request,
            iconId = R.drawable.ic_plus,
            route = Routes.ClassroomChoiceScreen.route
        ),
        BottomNavItem(
            labelId = R.string.key_transfer,
            iconId = R.drawable.ic_transfer,
            route = Routes.UserKeysScreen.route
        ),
        BottomNavItem(
            labelId = R.string.profile,
            iconId = R.drawable.ic_person,
            route = Routes.ProfileScreen.route
        ),
    )
}