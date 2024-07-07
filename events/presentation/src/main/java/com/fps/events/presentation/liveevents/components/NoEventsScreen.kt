package com.fps.events.presentation.liveevents.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fps.core.presentation.designsystem.ErrorIcon
import com.fps.core.presentation.designsystem.StoiximanWhite
import com.fps.events.presentation.R

@Composable
fun NoEventsScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center), // Center the content
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icon indicating no results
            AnimatedVisibility(visible = true) {
                Icon(
                    imageVector = ErrorIcon, // Use any appropriate icon
                    contentDescription = "No Events Icon",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 16.dp),
                    tint = StoiximanWhite
                )
            }

            // Text message
            Text(
                text = "No Events Found",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Additional info or button
            Text(
                text = stringResource(R.string.no_live_events_found),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = StoiximanWhite
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}