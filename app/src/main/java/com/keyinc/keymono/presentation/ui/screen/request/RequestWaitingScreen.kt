package com.keyinc.keymono.presentation.ui.screen.request

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.keyinc.keymono.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestWaitingScreen() {
    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = stringResource(id = R.string.app_label))
            })
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                RequestWaitingContent(
                    paddingValues = it,
                    showDialog = showDialog,
                    onClick = { showDialog.value = !showDialog.value }
                )


                AnimatedVisibility(
                    visible = showDialog.value,
                    enter = slideInVertically(
                        initialOffsetY = { fullHeight -> -fullHeight },
                        animationSpec = tween(durationMillis = 300)
                    ),
                    exit = slideOutVertically(
                        targetOffsetY = { fullHeight -> -fullHeight },
                        animationSpec = tween(durationMillis = 400)
                    )
                ) {

                    RequestAlert()
                    LaunchedEffect(key1 = showDialog.value) {
                        delay(3000L)
                        showDialog.value = false
                    }
                }
            }
        }
    )
}