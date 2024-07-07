package com.fps.events.presentation.liveevents

import com.fps.core.presentation.ui.UiText
import com.fps.events.presentation.liveevents.models.SportCategoryItemUi

data class LiveSportEventsState(
    val isLoading: Boolean = false,
    val isConnectedToNetwork: Boolean = false,
    val error: UiText? = null,
    val sportEvents: List<SportCategoryItemUi> = emptyList(),
    val filteredFavouritesSportEvents: List<SportCategoryItemUi> = emptyList()
)