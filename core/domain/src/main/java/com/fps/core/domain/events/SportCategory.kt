package com.fps.core.domain.events

data class SportCategory(
    val sportId: String,
    val sportName: String,
    val activeSportEvents: List<SportEvent>,
    val isShowOnlyFavourites: Boolean = false,
)