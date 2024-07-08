package com.fps.events.presentation.fakes

import com.fps.core.domain.events.EventsRepository
import com.fps.core.domain.events.SportCategory
import com.fps.core.domain.util.DataError
import com.fps.core.domain.util.EmptyResult
import com.fps.core.domain.util.Result
import com.fps.core.domain.util.asEmptyDataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeEventsRepository(
    private val localSportEventsDataSource: FakeLocalSportEventsDataSource,
    private val remoteLiveEventsDataSource: FakeRemoteEventsDataSource,
) : EventsRepository {

    var shouldReturnError = false
    var errorToReturn: Exception? = null

    private val liveEvents = mutableListOf<SportCategory>()

    override fun getLocalLiveEvents(): Flow<List<SportCategory>> {
        return localSportEventsDataSource.retrieveAllSportEvents()
    }

    override suspend fun getLiveEvents(): EmptyResult<DataError> {
        return remoteLiveEventsDataSource.getLiveEvents().also {
            if (it is Result.Success) {
                localSportEventsDataSource.saveSportEvents(it.data)
            }
        }.asEmptyDataResult()
    }


    override suspend fun setSportEventAsFavourite(sportId: String, isFavourite: Boolean) {
        localSportEventsDataSource.saveSportEventAsFavourite(sportId, isFavourite)
    }

    override suspend fun showFavouritesOnlyFromSportCategory(
        categoryId: String,
        isFavourite: Boolean
    ) {
        localSportEventsDataSource.saveFavouritesOnlyFromCategory(categoryId, isFavourite)
    }
}