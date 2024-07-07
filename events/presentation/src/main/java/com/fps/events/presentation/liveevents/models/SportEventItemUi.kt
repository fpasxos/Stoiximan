package com.fps.events.presentation.liveevents.models

data class SportEventItemUi(
    val id: String,
    val homeCompetitor: String,
    val awayCompetitor: String,
    val eventStartTime: Long,
    val isFavourite: Boolean
)