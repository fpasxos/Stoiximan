package com.fps.events.data.mappers

import com.fps.core.domain.events.SportEvent
import com.fps.core.domain.events.SportCategory
import com.fps.events.data.models.SportCategoryDto
import com.fps.events.data.models.SportEventItemDto

fun SportCategoryDto.toSportCategory(): SportCategory {
    return SportCategory(
        sportId = i ?: "",
        sportName = d ?: "",
        activeSportEvents = e?.map { it.toSportEvent() } ?: emptyList()
    )
}

fun SportEventItemDto.toSportEvent(): SportEvent {
    return SportEvent(
        eventId = i ?: "",
        sportId = si ?: "",
        fullEventName = d ?: "",
        eventStartTime = tt?.toLong() ?: -1
    )
}