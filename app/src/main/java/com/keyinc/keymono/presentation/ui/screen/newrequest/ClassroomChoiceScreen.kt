package com.keyinc.keymono.presentation.ui.screen.newrequest

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.keyinc.keymono.R
import com.keyinc.keymono.domain.entity.Classroom
import com.keyinc.keymono.presentation.ui.screen.newrequest.components.ClassroomChoiceItem
import com.keyinc.keymono.presentation.ui.theme.PaddingLarge
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import com.keyinc.keymono.presentation.ui.theme.Title

@Composable
fun ClassroomChoiceScreen(
    classrooms: List<Classroom>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Text(
                text = stringResource(id = R.string.new_classroom_booking_request),
                style = Title,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(PaddingLarge)
            )
        }

        items(classrooms) { classroom ->
            ClassroomChoiceItem(
                classroom = classroom,
                onClassroomClick = { /* TODO */ },
                modifier = Modifier.padding(horizontal = PaddingMedium)
            )
        }
    }
}