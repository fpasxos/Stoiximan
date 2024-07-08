package com.fps.events.presentation.fakes

import com.fps.core.domain.events.LocalSportEventsDataSource
import com.fps.core.domain.events.SportCategory
import com.fps.core.domain.events.SportCategoryId
import com.fps.core.domain.util.DataError
import com.fps.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeLocalSportEventsDataSource : LocalSportEventsDataSource {

    private val _sportEvents = MutableStateFlow<List<SportCategory>>(emptyList())
    private val sportEvents: StateFlow<List<SportCategory>> = _sportEvents.asStateFlow()

    override suspend fun saveSportEventAsFavourite(sportId: String, isFavourite: Boolean) {
        val updatedCategories = _sportEvents.value.map { category ->
            val updatedEvents = category.activeSportEvents.map { event ->
                if (event.eventId == sportId) {
                    event.copy(isFavourite = isFavourite)
                } else {
                    event
                }
            }
            category.copy(activeSportEvents = updatedEvents)
        }
        _sportEvents.value = updatedCategories
    }

    override suspend fun saveFavouritesOnlyFromCategory(
        categoryId: String,
        showFavouritesOnlyFromCategory: Boolean
    ) {
        val updatedCategories = _sportEvents.value.map { category ->
            if (category.sportId == categoryId) {
                val filteredEvents = if (showFavouritesOnlyFromCategory) {
                    category.activeSportEvents.filter { it.isFavourite }
                } else {
                    category.activeSportEvents
                }
                category.copy(
                    isShowOnlyFavourites = showFavouritesOnlyFromCategory,
                    activeSportEvents = filteredEvents
                )
            } else {
                category
            }
        }
        _sportEvents.value = updatedCategories
    }

    override suspend fun saveSportEvents(sportCategory: List<SportCategory>): Result<List<SportCategoryId>, DataError.Local> {
        _sportEvents.value = sportCategory
        return Result.Success(sportCategory.map { it.sportId })
    }

    override fun retrieveAllSportEvents(): Flow<List<SportCategory>> {
        return sportEvents
    }
}