package com.keyinc.keymono.presentation.ui.screen.transferrequests

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.component.TransferRequestCard
import com.keyinc.keymono.presentation.ui.screen.state.transferrequests.TransferRequestsUiState
import com.keyinc.keymono.presentation.ui.theme.Padding20
import com.keyinc.keymono.presentation.ui.theme.Padding64
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.theme.Title
import com.keyinc.keymono.presentation.viewModel.TransferRequestsViewModel

@Composable
fun TransferRequestsScreen(
    viewModel: TransferRequestsViewModel,
    modifier: Modifier = Modifier
) {
    val transferRequestsUiState by viewModel.transferRequestsUiState.collectAsStateWithLifecycle()

    when (transferRequestsUiState) {
        TransferRequestsUiState.Initial -> Unit
        TransferRequestsUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is TransferRequestsUiState.Error -> Log.e("TransferRequestsScreen", (transferRequestsUiState as TransferRequestsUiState.Error).message)
        is TransferRequestsUiState.Success -> {
            val transferRequests = (transferRequestsUiState as TransferRequestsUiState.Success).content

            LazyColumn(
                modifier = modifier
                    .padding(horizontal = Padding20)
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                if (transferRequests.items.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.height(Padding20))

                        Text(
                            text = "Список заявок на передачу ключей для вас",
                            textAlign = TextAlign.Center,
                            style = Title
                        )

                        Spacer(modifier = Modifier.height(Padding64))
                    }

                    items(transferRequests.items) {
                        TransferRequestCard(
                            classRoomName = "${it.key.classroom.number} (${it.key.classroom.building}) аудитория",
                            applicantName = it.applicantName
                        )
                    }
                } else {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.gray_cat),
                                modifier = Modifier.size(256.dp),
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.height(PaddingLarge))

                            Text(
                                text = "На текущий момент у вас нет входящих заявок на передачу ключей. Возможно, кто-то еще захочет поделиться с вами!",
                                textAlign = TextAlign.Center,
                                style = Title
                            )
                        }
                    }
                }
            }
        }
    }
}