package com.keyinc.keymono.presentation.ui.screen.newrequest.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.keyinc.keymono.R
import com.keyinc.keymono.presentation.ui.theme.Gray82
import com.keyinc.keymono.presentation.ui.theme.RequestFieldText

@Composable
fun ClassroomField(
    classroomNumber: Int,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = stringResource(R.string.classroom_description, classroomNumber),
        onValueChange = {},
        enabled = false,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_classroom),
                contentDescription = stringResource(id = R.string.classroom_number)
            )
        },
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Gray82,
            disabledTextColor = Color.Black,
            disabledLeadingIconColor = Color.Black
        ),
        textStyle = RequestFieldText
    )
}