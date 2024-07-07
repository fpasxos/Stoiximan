package com.fps.events.presentation.liveevents.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.fps.core.presentation.designsystem.StoiximanBlack
import com.fps.core.presentation.designsystem.StoiximanBlue
import com.fps.core.presentation.designsystem.StoiximanGray
import com.fps.core.presentation.designsystem.StoiximanRed
import com.fps.core.presentation.designsystem.StoiximanWhite
import com.fps.events.presentation.liveevents.SportEventsAction
import com.fps.events.presentation.liveevents.models.SportCategoryItemUi

@Composable
fun ExpandableSportEventCard(
    sportCategoryItemUi: SportCategoryItemUi,
    onSetFavouriteItem: (SportEventsAction) -> Unit,
    onShowOnlyFavourites: (SportEventsAction) -> Unit,
    titleFontSize: TextUnit = MaterialTheme.typography.titleLarge.fontSize,
    titleFontWeight: FontWeight = FontWeight.Bold
) {

    var expandedState by remember {
        mutableStateOf(false)
    }

    var isFavouritesChecked by remember { mutableStateOf(sportCategoryItemUi.isShowOnlyFavourites) }

    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f, label = ""
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(StoiximanWhite)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = RectangleShape,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .background(StoiximanWhite)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Sport Icon
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(StoiximanRed, CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = sportCategoryItemUi.sportName,
                    fontSize = titleFontSize,
                    fontWeight = titleFontWeight,
                    maxLines = 1,
                    color = StoiximanBlack,
                    overflow = TextOverflow.Ellipsis
                )

                Switch(
                    checked = isFavouritesChecked,
                    colors = SwitchDefaults.colors(
                        checkedIconColor = StoiximanBlack,
                        checkedThumbColor = StoiximanBlue,
                        uncheckedTrackColor = StoiximanGray

                    ),
                    thumbContent = {
                        if (isFavouritesChecked) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                tint = StoiximanWhite,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                tint = StoiximanWhite,
                                contentDescription = null
                            )
                        }
                    }, onCheckedChange = { checked ->
                        isFavouritesChecked = checked
                        onShowOnlyFavourites(
                            SportEventsAction.OnShowOnlyFavourites(
                                id = sportCategoryItemUi.sportId,
                                isFavouritesChecked = isFavouritesChecked
                            )
                        )
                    })

                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(150.dp),
                        imageVector = Icons.Default.KeyboardArrowDown,
                        tint = Color.Black,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }

            if (expandedState) {
                if (sportCategoryItemUi.activeSportEvents.isEmpty()) {
                    NoEventsScreen(
                        modifier = Modifier
                            .background(StoiximanGray)
                            .padding(top = 10.dp),
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .height(calculateRowsWithFixedHeight(sportCategoryItemUi.activeSportEvents.size).dp)
                    ) {
                        LazyVerticalGrid(
                            modifier = Modifier
                                .background(StoiximanGray),
                            columns = GridCells.Fixed(4)
                        ) {
                            items(sportCategoryItemUi.activeSportEvents) {
                                SportEventItem(
                                    sportItemUi = it,
                                    onFavouriteClick = { id, isFavourite ->
                                        onSetFavouriteItem(
                                            SportEventsAction.OnFavouriteClick(
                                                id,
                                                isFavourite
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun calculateRowsWithFixedHeight(items: Int): Int {
    val rows = items.div(4) + 1
    return rows.times(210)
}
