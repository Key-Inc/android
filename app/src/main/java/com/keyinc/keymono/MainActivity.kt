package com.keyinc.keymono

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.keyinc.keymono.presentation.ui.navigation.ApplicationNavHost
import com.keyinc.keymono.presentation.viewModel.NewRequestViewModel
import com.keyinc.keymono.presentation.viewModel.ProfileViewModel
import com.keyinc.keymono.presentation.viewModel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val registrationViewModel: RegistrationViewModel by viewModels()
    private val newRequestViewModel: NewRequestViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationNavHost(
                registrationViewModel = registrationViewModel,
                newRequestViewModel = newRequestViewModel,
                profileViewModel = profileViewModel
            )
        }
    }
}