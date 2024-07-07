package com.fps.core.domain.events

data class SportEvent(
    val eventId: String,
    val sportId: String,
    val fullEventName: String,
    val eventStartTime: Long,
    val isFavourite: Boolean = false
)