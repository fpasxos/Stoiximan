package com.fps.events.presentation.liveevents.mappers

import com.fps.core.domain.events.SportCategory
import com.fps.core.domain.events.SportEvent
import com.fps.events.presentation.liveevents.models.SportCategoryItemUi
import com.fps.events.presentation.liveevents.models.SportEventItemUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

fun SportCategory.toSportCategoryItemUi(viewModelScope: CoroutineScope): SportCategoryItemUi {
    return SportCategoryItemUi(
        sportId = sportId,
        sportName = sportName,
        isShowOnlyFavourites = isShowOnlyFavourites,
        // we filter out any event which its start time is in the past
        activeSportEvents = activeSportEvents.filter {
            it.eventStartTime * 1000 > System.currentTimeMillis()
        }.map { it.toSportEventItemUi(viewModelScope) }
    )
}

fun SportEvent.toSportEventItemUi(viewModelScope: CoroutineScope): SportEventItemUi {
    val splitText = fullEventName.split(Regex("\\s*-\\s*"))
    val homeTeam = splitText[0]
    val awayTeam = splitText[1]

    val initialTimeLeft = eventStartTime * 1000 - System.currentTimeMillis()
    val timerFlow = MutableStateFlow(initialTimeLeft)
    startTimer(timerFlow, viewModelScope)

    return SportEventItemUi(
        id = eventId,
        homeCompetitor = homeTeam,
        awayCompetitor = awayTeam,
        eventStartTime = timerFlow, // Using StateFlow<Long> for the event start time
        isFavourite = isFavourite
    )
}

private fun startTimer(timerFlow: MutableStateFlow<Long>, scope: CoroutineScope) {
    scope.launch(Dispatchers.Default) {
        while (timerFlow.value > 0) {
            delay(1000L)
            timerFlow.value -= 1000
        }
    }
}