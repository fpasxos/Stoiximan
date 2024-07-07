package com.fps.events.presentation.liveevents

sealed interface SportEventsAction {
    data class OnFavouriteClick(val id: String, val isFavourite: Boolean) : SportEventsAction
    data class OnShowOnlyFavourites(val id: String, val isFavouritesChecked: Boolean) :
        SportEventsAction
}