package com.fps.events.presentation.liveevents.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.fps.core.presentation.designsystem.StoiximanYellow

@Composable
fun FavouriteIcon(
    isFavourite: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = {
            onClick()
        },
        enabled = true,
        content = {
            if (isFavourite) {
                Icon(
                    imageVector = Icons.Default.Star,
                    tint = StoiximanYellow,
                    contentDescription = "Favourite"
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Favourite"
                )
            }
        }
    )
}