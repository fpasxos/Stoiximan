package com.fps.core.data.liveevents

import com.fps.core.domain.events.SportCategory
import com.fps.core.domain.events.EventsRepository
import com.fps.core.domain.events.LocalSportEventsDataSource
import com.fps.core.domain.events.RemoteEventsDataSource
import com.fps.core.domain.util.DataError
import com.fps.core.domain.util.EmptyResult
import com.fps.core.domain.util.Result
import com.fps.core.domain.util.asEmptyDataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow

class LiveEventsRepositoryImpl(
    private val localSportEventsDataSource: LocalSportEventsDataSource,
    private val remoteLiveEventsDataSource: RemoteEventsDataSource,
    private val applicationScope: CoroutineScope
) : EventsRepository {
    override fun getLocalLiveEvents(): Flow<List<SportCategory>> {
        return localSportEventsDataSource.retrieveAllSportEvents()
    }

    override suspend fun getLiveEvents(): EmptyResult<DataError> {
        return when (val result = remoteLiveEventsDataSource.getLiveEvents()) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                applicationScope.async {
                    localSportEventsDataSource.saveSportEvents(result.data).asEmptyDataResult()
                }.await()
            }
        }
    }

    override suspend fun setSportEventAsFavourite(sportId: String, isFavourite: Boolean) {
        localSportEventsDataSource.saveSportEventAsFavourite(
            sportId = sportId,
            isFavourite = isFavourite
        )
    }

    override suspend fun showFavouritesOnlyFromSportCategory(
        categoryId: String,
        isFavourite: Boolean
    ) {
        localSportEventsDataSource.saveFavouritesOnlyFromCategory(
            categoryId = categoryId,
            showFavouritesOnlyFromCategory = isFavourite
        )
    }
}