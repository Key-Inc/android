package com.keyinc.keymono.presentation.ui.screen.newrequest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.ClassroomChoiceItem
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import com.keyinc.keymono.presentation.ui.theme.PaddingSmall
import com.keyinc.keymono.presentation.ui.theme.Title
import com.keyinc.keymono.presentation.viewModel.NewRequestViewModel

private const val TAG = "ClassroomChoiceScreen"

@Composable
fun ClassroomChoiceScreen(
    modifier: Modifier = Modifier,
    viewModel: NewRequestViewModel = hiltViewModel()
) {
    val classroomPaginationState by viewModel.classroomPaginationState.collectAsStateWithLifecycle()
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = scrollState
    ) {
        item {
            Text(
                modifier = Modifier.padding(PaddingLarge),
                text = stringResource(id = R.string.new_classroom_booking_request),
                style = Title,
                textAlign = TextAlign.Center
            )
        }

        items(classroomPaginationState.items.size) { classroomIndex ->
            LaunchedEffect(scrollState) {
                if (classroomIndex >= classroomPaginationState.items.size - 1
                    && !classroomPaginationState.isEndReached
                    && !classroomPaginationState.isLoading
                ) {
                    viewModel.loadNextClassrooms()
                }
            }

            val classroom = classroomPaginationState.items[classroomIndex]

            ClassroomChoiceItem(
                modifier = Modifier.padding(horizontal = PaddingMedium),
                classroom = classroom,
                onClassroomClick = { /* TODO */ }
            )
        }

        item {
            if (classroomPaginationState.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(PaddingSmall),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}