package com.keyinc.keymono

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.keyinc.keymono.presentation.ui.navigation.RootNavigation
import com.keyinc.keymono.presentation.viewModel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val registrationViewModel: RegistrationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val activity = (LocalContext.current as? Activity)

            RootNavigation(
                navController = navController,
                onCloseApp = {
                    activity?.finish()
                },
                registrationViewModel = registrationViewModel
            )
        }
    }
}