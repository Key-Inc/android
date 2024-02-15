package com.keyinc.keymono.presentation.ui.screen.onboarding

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.keyinc.keymono.R


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = "id:pixel_7_pro")
@Composable
fun OnBoardingScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = stringResource(id = R.string.app_label))
            })
        },
        content = {
            OnBoardingContent(paddingValues = it)
        }
    )
}