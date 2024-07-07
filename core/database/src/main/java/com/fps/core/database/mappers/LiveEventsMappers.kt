package com.fps.core.database.mappers

import com.fps.core.database.entity.SportEventEntity
import com.fps.core.database.entity.SportCategoryEntity
import com.fps.core.database.entity.SportCategoryWithEvents
import com.fps.core.domain.events.SportEvent
import com.fps.core.domain.events.SportCategory

fun SportCategoryWithEvents.toSportCategory(): SportCategory {
    return SportCategory(
        sportId = sportCategory.sportId,
        sportName = sportCategory.sportName,
        activeSportEvents = activeSportEvents.map { it.toLiveSportEvent() },
        isShowOnlyFavourites = sportCategory.isShowOnlyFavourites
    )
}

fun SportEventEntity.toLiveSportEvent(): SportEvent {
    return SportEvent(
        eventId = id,
        sportId = sportId,
        fullEventName = fullEventName,
        eventStartTime = eventStartTime,
        isFavourite = isFavourite
    )
}

fun SportCategory.toSportCategoryEntity(): SportCategoryEntity {
    return SportCategoryEntity(
        sportId = sportId,
        sportName = sportName,
        isShowOnlyFavourites = isShowOnlyFavourites,
    )
}

fun SportEvent.toSportEventEntity(): SportEventEntity {
    return SportEventEntity(
        id = eventId,
        sportId = sportId,
        fullEventName = fullEventName,
        eventStartTime = eventStartTime,
        isFavourite = isFavourite
    )
}