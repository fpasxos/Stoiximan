package com.fps.events.presentation.fakes

import com.fps.core.domain.events.RemoteEventsDataSource
import com.fps.core.domain.events.SportCategory
import com.fps.core.domain.util.DataError
import com.fps.core.domain.util.Result

class FakeRemoteEventsDataSource : RemoteEventsDataSource {

    private var listOfSportCategories = mutableListOf(
        SportCategory(
            sportId = "1234",
            sportName = "Football",
            activeSportEvents = arrayListOf(),
            isShowOnlyFavourites = true
        )
    )

    override suspend fun getLiveEvents(): Result<List<SportCategory>, DataError.Network> {
        return Result.Success(listOfSportCategories)

    }
}