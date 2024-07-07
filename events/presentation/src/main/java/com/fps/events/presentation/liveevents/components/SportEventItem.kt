package com.fps.events.presentation.liveevents.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fps.core.presentation.designsystem.StoiximanTheme
import com.fps.events.presentation.liveevents.models.SportEventItemUi
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.fps.core.presentation.designsystem.StoiximanRed
import com.fps.core.presentation.designsystem.StoiximanWhite
import com.fps.events.presentation.R

@Composable
fun SportEventItem(
    sportItemUi: SportEventItemUi,
    modifier: Modifier = Modifier,
    onFavouriteClick: (String, Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CountDownTimerItem(
                id = sportItemUi.id,
                timerValue = sportItemUi.eventStartTime
            )

            FavouriteIcon(
                isFavourite = sportItemUi.isFavourite
            ) {
                onFavouriteClick(
                    sportItemUi.id,
                    !sportItemUi.isFavourite
                )
            }
            Text(
                textAlign = TextAlign.Center,
                color = StoiximanWhite,
                text = sportItemUi.homeCompetitor,
                maxLines = 2,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.vs),
                fontSize = 12.sp,
                color = StoiximanRed
            )
            Text(
                textAlign = TextAlign.Center,
                color = StoiximanWhite,
                maxLines = 2,
                text = sportItemUi.awayCompetitor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
private fun EventItemPreview() {
    StoiximanTheme {
        SportEventItem(
            sportItemUi = SportEventItemUi(
                id = "",
                homeCompetitor = "Olympiakos",
                awayCompetitor = "Panathinaikos",
                eventStartTime = 10000000,
                isFavourite = false
            ),
            onFavouriteClick = { String, Boolean -> }

        )
    }
}