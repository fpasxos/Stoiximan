package com.fps.events.presentation.liveevents.models

data class SportCategoryItemUi(
    val sportId: String,
    val sportName: String,
    val isShowOnlyFavourites: Boolean,
    val activeSportEvents: List<SportEventItemUi>
)