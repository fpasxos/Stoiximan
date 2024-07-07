package com.fps.events.presentation.liveevents.mappers

import com.fps.core.domain.events.SportCategory
import com.fps.core.domain.events.SportEvent
import com.fps.events.presentation.liveevents.models.SportCategoryItemUi
import com.fps.events.presentation.liveevents.models.SportEventItemUi

fun SportCategory.toSportCategoryItemUi(): SportCategoryItemUi {
    return SportCategoryItemUi(
        sportId = sportId,
        sportName = sportName,
        isShowOnlyFavourites = isShowOnlyFavourites,
        // we filter out, any event which its start time is in the past
        activeSportEvents = activeSportEvents.filter {
            it.eventStartTime * 1000 > System.currentTimeMillis()
        }
            .map { it.toSportEventItemUi() }
    )
}

fun SportEvent.toSportEventItemUi(): SportEventItemUi {
    val splitText = fullEventName.split(Regex("\\s*-\\s*"))
    val homeTeam = splitText[0]
    val awayTeam = splitText[1]

    return SportEventItemUi(
        id = eventId,
        homeCompetitor = homeTeam,
        awayCompetitor = awayTeam,
        eventStartTime = eventStartTime * 1000, // we convert this to have eventStartTime in Milliseconds(now in Unix/seconds)
        isFavourite = isFavourite
    )
}