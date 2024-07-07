package com.fps.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fps.core.presentation.designsystem.ErrorIcon
import com.fps.core.presentation.designsystem.StoiximanBlack
import com.fps.core.presentation.designsystem.R
import com.fps.core.presentation.designsystem.StoiximanConnectivityDarkRed

@Composable
fun ErrorScreen(
    errorText: String,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = ErrorIcon,
                contentDescription = null,
                tint = StoiximanConnectivityDarkRed
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.oops),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = StoiximanBlack
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                text = errorText,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}