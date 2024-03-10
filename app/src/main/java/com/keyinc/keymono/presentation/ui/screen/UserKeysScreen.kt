package com.keyinc.keymono.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
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
import com.keyinc.keymono.domain.entity.UserKeyDto
import com.keyinc.keymono.presentation.ui.component.AccentButton
import com.keyinc.keymono.presentation.ui.screen.state.keytransfer.KeyTransferState
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.CardBackGround
import com.keyinc.keymono.presentation.ui.theme.FontMedium
import com.keyinc.keymono.presentation.ui.theme.FontSmall
import com.keyinc.keymono.presentation.ui.theme.Regular
import com.keyinc.keymono.presentation.ui.theme.Title
import com.keyinc.keymono.presentation.ui.util.noRippleClickable
import com.keyinc.keymono.presentation.viewModel.KeyTransferViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserKeysScreen(
    keyTransferViewModel: KeyTransferViewModel = hiltViewModel(),
    navigateOnSearchUser: (String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val state by keyTransferViewModel.keysTransferState.collectAsStateWithLifecycle()


    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            ),
            title = { Text("Ключи для передачи", style = Title) }
        )
    },
        content = {
            when (state) {

                is KeyTransferState.Initial -> {
                    keyTransferViewModel.getUserAvailableKeys()
                }

                is KeyTransferState.Loading -> {
                    LoadingKeys()
                }

                is KeyTransferState.Content -> {
                    val keyTransferState = state as KeyTransferState.Content
                    UserKeys(
                        paddingValues = it,
                        requestList = keyTransferState.keyList,
                        navigateOnSearchUser = navigateOnSearchUser
                    )
                }

                is KeyTransferState.NoKeys -> {
                    NoKeys()
                }

                is KeyTransferState.Unauthorized -> {
                    onNavigateToLogin()
                }

                is KeyTransferState.Error -> {
                    KeysErrorScreen {
                        keyTransferViewModel.restoreState()
                    }
                }
            }
        })

}


@Composable
private fun KeysCard(
    onClick: (String) -> Unit = {},
    id: String,
    classroomName: String,
    buildingName: String,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .noRippleClickable { onClick(classroomName) }
            .padding(bottom = 16.dp, top = 16.dp, end = 32.dp, start = 32.dp),
        colors = CardDefaults.cardColors(

            containerColor = CardBackGround
        )
    ) {
        Text(
            text = "$classroomName кабинет",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            style = Regular,
            fontSize = FontMedium
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.uni_hat),
                contentDescription = null
            )
            Text(
                text = "Учебная аудитория $buildingName-го корпуса",
                style = Regular,
                fontSize = FontSmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
private fun UserKeys(
    paddingValues: PaddingValues,
    requestList: List<UserKeyDto>,
    navigateOnSearchUser: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .background(Color.White)
            .fillMaxSize()
    ) {
        items(requestList.size) { index ->
            KeysCard(
                onClick = { navigateOnSearchUser(requestList[index].id) },
                classroomName = requestList[index].classroom.number.toString(),
                buildingName = requestList[index].classroom.building.toString(),
                id = requestList[index].id

            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun NoKeys() {
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
            text = "У вас нет доступных ключей для передачи",
            textAlign = TextAlign.Center,
            style = Title
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun LoadingKeys() {
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
private fun KeysErrorScreen(onUpdate: () -> Unit = {}) {
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