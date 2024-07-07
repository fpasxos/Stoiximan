package com.fps.core.domain.events

import com.fps.core.domain.util.DataError
import com.fps.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

typealias SportCategoryId = String

interface LocalSportEventsDataSource {
    suspend fun saveSportEventAsFavourite(sportId: String, isFavourite: Boolean)
    suspend fun saveFavouritesOnlyFromCategory(
        categoryId: String,
        showFavouritesOnlyFromCategory: Boolean
    )

    suspend fun saveSportEvents(sportCategory: List<SportCategory>): Result<List<SportCategoryId>, DataError.Local>
    fun retrieveAllSportEvents(): Flow<List<SportCategory>>
}