package com.fps.core.domain.events

import com.fps.core.domain.util.DataError
import com.fps.core.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    fun getLocalLiveEvents(): Flow<List<SportCategory>>
    suspend fun getLiveEvents(): EmptyResult<DataError>
    suspend fun setSportEventAsFavourite(sportId: String, isFavourite: Boolean)
    suspend fun showFavouritesOnlyFromSportCategory(categoryId: String, isFavourite: Boolean)
}