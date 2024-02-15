package com.keyinc.keymono

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.keyinc.keymono.data.MockClassrooms
import com.keyinc.keymono.presentation.ui.screen.newrequest.ClassroomChoiceScreen
import com.keyinc.keymono.presentation.ui.theme.KeyMonoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeyMonoTheme {
                ClassroomChoiceScreen(classrooms = MockClassrooms.classrooms)
            }
        }
    }
}