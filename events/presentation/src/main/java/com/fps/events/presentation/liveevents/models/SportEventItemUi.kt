package com.fps.events.presentation.liveevents.models

import kotlinx.coroutines.flow.StateFlow

data class SportEventItemUi(
    val id: String,
    val homeCompetitor: String,
    val awayCompetitor: String,
    val eventStartTime: StateFlow<Long>,
    val isFavourite: Boolean
)