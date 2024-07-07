package com.fps.events.presentation.fakes

import com.fps.core.domain.events.EventsRepository
import com.fps.core.domain.events.SportCategory
import com.fps.core.domain.events.SportEvent
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
    private val liveEvents = mutableListOf<SportEvent>()

    fun setLiveEvents(events: List<SportEvent>) {
        liveEvents.clear()
        liveEvents.addAll(events)
    }

    override fun getLocalLiveEvents(): Flow<List<SportCategory>> {
        return flowOf()
    }

    override suspend fun getLiveEvents(): EmptyResult<DataError> {
        return Result.Success("").asEmptyDataResult()
    }


    override suspend fun setSportEventAsFavourite(sportId: String, isFavourite: Boolean) {

    }

    override suspend fun showFavouritesOnlyFromSportCategory(
        categoryId: String,
        isFavourite: Boolean
    ) {

    }
}