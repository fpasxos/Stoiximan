package com.fps.events.presentation.liveevents.components

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fps.core.presentation.designsystem.StoiximanBlue
import com.fps.core.presentation.designsystem.StoiximanWhite
import com.fps.events.presentation.liveevents.models.formatTime
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("RememberReturnType")
@Composable
fun CountDownTimerItem(
    timeLeft: StateFlow<Long>,
    modifier: Modifier = Modifier
) {
    val time by timeLeft.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .border(width = 1.5.dp, color = StoiximanBlue, shape = RectangleShape)
                .padding(2.dp),
            color = StoiximanWhite,
            text = time.formatTime(),
            fontSize = 12.sp
        )
    }
}