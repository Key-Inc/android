package com.keyinc.keymono.presentation.ui.userRequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.keymono.R
import com.keyinc.keymono.domain.entity.RequestDTO
import com.keyinc.keymono.presentation.ui.component.AccentButton
import com.keyinc.keymono.presentation.ui.component.RequestCard
import com.keyinc.keymono.presentation.ui.screen.state.userrequest.UserRequestState
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.Title
import com.keyinc.keymono.presentation.ui.util.DateConverterUtil.extractTime
import com.keyinc.keymono.presentation.viewModel.UserRequestViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRequestScreen(
    userRequestViewModel: UserRequestViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit
) {

    val requestState by userRequestViewModel.requestState.collectAsStateWithLifecycle()


    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            ),
            title = { Text("Список текущих заявок", style = Title) }
        )
    },
        content = {
            when (requestState) {
                is UserRequestState.Initial -> userRequestViewModel.getUserRequest()

                is UserRequestState.NoRequest -> {
                    NoRequest()
                }

                is UserRequestState.Loading -> {
                    LoadingRequest()
                }

                is UserRequestState.Error -> {
                    RequestErrorScreen {
                        userRequestViewModel.restoreState()
                    }
                }

                is UserRequestState.Unauthorized -> onNavigateToLogin()

                is UserRequestState.Content -> {
                    val content = requestState as UserRequestState.Content
                    UserRequest(
                        paddingValues = it,
                        requestList = content.requestList
                    )
                }
            }
        }
    )
}


@Composable
@Preview(showBackground = true)
fun LoadingRequest() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(16.dp)
                .size(128.dp),
            color = Accent,
            strokeWidth = 8.dp
        )
    }
}


@Composable
fun RequestErrorScreen(onUpdate: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.chel_icon),
            modifier = Modifier.size(256.dp),
            contentDescription = null
        )
        Text(
            text = "Произошла неожиданная ошибка, попробуйте еще раз",
            textAlign = TextAlign.Center,
            style = Title
        )
        AccentButton(
            enabled = true,
            onClick = onUpdate,
            text = "Повторить"
        )
    }
}

@Composable
fun UserRequest(paddingValues: PaddingValues, requestList: List<RequestDTO>) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .background(Color.White)
            .padding(start = 32.dp)
            .fillMaxSize()
    ) {
        items(requestList.size) { index ->
            RequestCard(
                classRoomName = requestList[index].classroom.number.toString() + " (" + requestList[index].classroom.building + ") аудитория",
                requestDate = requestList[index].startDate,
                requestStatus = requestList[index].status,
                requestType = requestList[index].endDateOfRecurrence,
                requestTime = extractTime(requestList[index].startDate) + " - " + extractTime(
                    requestList[index].endDate
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NoRequest() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.gray_cat),
            modifier = Modifier.size(256.dp),
            contentDescription = null
        )
        Text(
            text = "У вас еще нет заявок на ключи, давайте вместе выберем удобный для вас кабинет",
            textAlign = TextAlign.Center,
            style = Title
        )
    }
}