package com.fps.events.presentation.liveevents.components

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.fps.events.presentation.liveevents.models.formatTime
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.fps.core.presentation.designsystem.StoiximanBlue
import com.fps.core.presentation.designsystem.StoiximanWhite
import kotlinx.coroutines.delay

@SuppressLint("RememberReturnType")
@Composable
fun CountDownTimerItem(
    id: String,
    timerValue: Long
) {
    var timeLeft by remember(id) { mutableLongStateOf(timerValue - System.currentTimeMillis()) }

    LaunchedEffect(key1 = id) {
        while (timeLeft > 1000) {
            delay(1000L)
            timeLeft -= 1000
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .border(width = 1.5.dp, color = StoiximanBlue, shape = RectangleShape)
                .padding(2.dp),
            color = StoiximanWhite,
            text = timeLeft.formatTime(),
            fontSize = 12.sp
        )
    }
}