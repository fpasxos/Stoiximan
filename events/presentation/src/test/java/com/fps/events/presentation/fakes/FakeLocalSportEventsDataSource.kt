package com.fps.events.presentation.fakes

import com.fps.core.domain.events.LocalSportEventsDataSource
import com.fps.core.domain.events.SportCategory
import com.fps.core.domain.events.SportCategoryId
import com.fps.core.domain.util.DataError
import com.fps.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeLocalSportEventsDataSource : LocalSportEventsDataSource {
    override suspend fun saveSportEventAsFavourite(sportId: String, isFavourite: Boolean) {
    }

    override suspend fun saveFavouritesOnlyFromCategory(
        categoryId: String,
        showFavouritesOnlyFromCategory: Boolean
    ) {
    }

    override suspend fun saveSportEvents(sportCategory: List<SportCategory>): Result<List<SportCategoryId>, DataError.Local> {
        return Result.Success(listOf())
    }

    override fun retrieveAllSportEvents(): Flow<List<SportCategory>> {
        val listOfSportCategories = listOf(
            SportCategory(
                sportId = "FOOT",
                sportName = "FOOTBALL",
                activeSportEvents = arrayListOf(),
                isShowOnlyFavourites = true
            )
        )
        return flowOf(listOfSportCategories)
    }
}