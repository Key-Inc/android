package com.keyinc.keymono.presentation.ui.screen.newrequest.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.R
import com.keyinc.keymono.domain.entity.Classroom
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.Gray62
import com.keyinc.keymono.presentation.ui.theme.PaddingSmall
import com.keyinc.keymono.presentation.ui.theme.Regular

@Composable
fun ClassroomChoiceItem(
    classroom: Classroom,
    onClassroomClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable {
            onClassroomClick()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = PaddingSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.classroom_short_desc, classroom.number),
                style = Regular
            )

            Spacer(modifier = Modifier.height(PaddingSmall))

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_right_alt),
                tint = Accent,
                contentDescription = stringResource(id = R.string.choose_classroom)
            )
        }

        //TODO remove to HorizontalDivider
        Divider(
            thickness = (0.5).dp,
            color = Gray62
        )
    }
}