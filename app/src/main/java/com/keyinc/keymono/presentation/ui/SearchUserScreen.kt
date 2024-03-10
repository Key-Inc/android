package com.keyinc.keymono.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.component.AccentButton
import com.keyinc.keymono.presentation.ui.component.AccentTextField
import com.keyinc.keymono.presentation.ui.screen.state.SearchUserState
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.CardBackGround
import com.keyinc.keymono.presentation.ui.theme.FontMedium
import com.keyinc.keymono.presentation.ui.theme.FontSmall
import com.keyinc.keymono.presentation.ui.theme.Regular
import com.keyinc.keymono.presentation.ui.util.noRippleClickable
import com.keyinc.keymono.presentation.viewModel.SearchUserViewModel


@Composable
fun SearchUserScreen(
    requestId: String,
    searchUserViewModel: SearchUserViewModel = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current
    val state by searchUserViewModel.searchUserState.collectAsStateWithLifecycle()
    val uiState by searchUserViewModel.uiState.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                focusManager.clearFocus()
            }
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        AccentTextField(
            textFieldValue = uiState.userName,
            label = "Введите имя пользователя",
            errorId = null,
            onValueChange = searchUserViewModel::onUserNameChanged
        )

        when (state) {
            is SearchUserState.Content -> {
                val content = (state as SearchUserState.Content).userPagedListDto
                AnimatedVisibility(visible = true) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(content.items.size) { index ->
                            UserCard(
                                userName = content.items[index].fullName,
                                userRole = content.items[index].userRole,
                                email = content.items[index].email,
                                userId = content.items[index].id,
                                onClick = {
                                    searchUserViewModel.transferKeyForUser(
                                        requestId,
                                        content.items[index].id
                                    )
                                }
                            )
                        }
                    }
                }
            }

            is SearchUserState.Loading -> {
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

            is SearchUserState.Error -> {
                Text(
                    text = (state as SearchUserState.Error).message,
                    modifier = Modifier.fillMaxWidth(),
                    style = Regular,
                    textAlign = TextAlign.Center,
                    fontSize = FontMedium
                )
            }

            is SearchUserState.Unauthorized -> {

            }

            SearchUserState.Initial -> searchUserViewModel.getUserByName(uiState.userName)
        }
    }
}


@Composable
fun UserDialog(
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
    userName: String?,
    userRole: String?,
    email: String?
) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            surface = Color.Black,
            onSurface = Color.Black
        )
    ) {
        Dialog(onDismissRequest = { }) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text("Подтвердите передачу ключа", style = Regular, fontSize = FontMedium)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp, start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painterResource(R.drawable.person_icon),
                            colorFilter = ColorFilter.tint(Accent),
                            contentDescription = null
                        )

                        Text(
                            text = userName ?: "",
                            modifier = Modifier.padding(start = 16.dp),
                            style = Regular,
                            fontSize = FontSmall
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painterResource(R.drawable.uni_hat),
                            colorFilter = ColorFilter.tint(Accent),
                            contentDescription = null
                        )
                        Text(
                            text = userRole ?: "",
                            modifier = Modifier.padding(start = 16.dp),
                            style = Regular,
                            fontSize = FontSmall
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, start = 16.dp, bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_mail),
                            colorFilter = ColorFilter.tint(Accent),
                            contentDescription = null
                        )
                        Text(
                            text = email ?: "",
                            modifier = Modifier.padding(start = 16.dp),
                            style = Regular,
                            fontSize = FontSmall
                        )
                    }
                    AccentButton(enabled = true, text = "Подтвердить", onClick = onConfirm)
                    Text(
                        text = "Отмена",
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .noRippleClickable { onDismiss() },
                        style = Regular,
                        fontSize = FontMedium
                    )
                }
            }
        }
    }
}


@Composable
fun UserCard(
    onClick: () -> Unit = {},
    userName: String?,
    userRole: String?,
    email: String?,
    userId: String,
) {

    val userRoleTranslated = when (userRole) {
        "Student" -> "Студент"
        "Teacher" -> "Преподаватель"
        else -> "Студент"
    }

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        UserDialog(
            userName = userName,
            userRole = userRoleTranslated,
            email = email,
            onDismiss = { showDialog = false },
            onConfirm = { onClick(); showDialog = false }
        )
    }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .noRippleClickable { showDialog = true }
            .padding(start = 16.dp, bottom = 16.dp, top = 16.dp, end = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardBackGround
        ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(R.drawable.person_icon),
                    modifier = Modifier.size(32.dp),
                    colorFilter = ColorFilter.tint(Accent),
                    contentDescription = null
                )

                Text(
                    text = userName ?: "",
                    modifier = Modifier.padding(start = 8.dp),
                    style = Regular,
                    fontSize = FontMedium
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(R.drawable.uni_hat),
                    colorFilter = ColorFilter.tint(Accent),
                    contentDescription = null
                )
                Text(
                    text = userRoleTranslated,
                    modifier = Modifier.padding(start = 16.dp),
                    style = Regular,
                    fontSize = FontSmall
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 32.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_mail),
                    colorFilter = ColorFilter.tint(Accent),
                    contentDescription = null
                )
                Text(
                    text = email ?: "",
                    modifier = Modifier.padding(start = 16.dp),
                    style = Regular,
                    fontSize = FontSmall
                )
            }
        }
    }
}