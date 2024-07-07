package com.fps.core.database

import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteFullException
import com.fps.core.database.dao.SportEventsDao
import com.fps.core.database.mappers.toSportCategory
import com.fps.core.database.mappers.toSportCategoryEntity
import com.fps.core.database.mappers.toSportEventEntity
import com.fps.core.domain.events.SportCategory
import com.fps.core.domain.events.LocalSportEventsDataSource
import com.fps.core.domain.events.SportCategoryId
import com.fps.core.domain.util.DataError
import com.fps.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class RoomLocalSportEventsDataSource(
    private val sportEventsDao: SportEventsDao
) : LocalSportEventsDataSource {
    override suspend fun saveSportEventAsFavourite(sportId: String, isFavourite: Boolean) {
        sportEventsDao.saveSportsEventAsFavourite(sportId = sportId, setIsFavourite = isFavourite)
    }

    override suspend fun saveFavouritesOnlyFromCategory(
        categoryId: String,
        showFavouritesOnlyFromCategory: Boolean
    ) {
        sportEventsDao.saveShowSportCategoryFavouritesOnly(
            categoryId = categoryId,
            showFavouritesOnlyFromCategory = showFavouritesOnlyFromCategory
        )
    }

    override suspend fun saveSportEvents(sportCategory: List<SportCategory>): Result<List<SportCategoryId>, DataError.Local> {
        return try {
            val sportCategoryEntity = sportCategory.map { it.toSportCategoryEntity() }
            val sportEventEntity =
                sportCategory.flatMap { it.activeSportEvents }.map { it.toSportEventEntity() }

            // Retrieve current favorites
            val currentSportCategoryFavorites =
                sportEventsDao.getFavouriteSportCategories().associateBy { it.sportId }
            val currentSportEventFavorites =
                sportEventsDao.getFavouriteSportEvents().associateBy { it.id }

            // Merge current category favorites with new data
            val mergedSportCategoryEntity = sportCategoryEntity.map { event ->
                event.copy(
                    isShowOnlyFavourites = currentSportCategoryFavorites[event.sportId]?.isShowOnlyFavourites
                        ?: event.isShowOnlyFavourites
                )
            }

            // Merge current sport favorites with new data
            val mergedSportEventEntity = sportEventEntity.map { event ->
                event.copy(
                    isFavourite = currentSportEventFavorites[event.id]?.isFavourite
                        ?: event.isFavourite
                )
            }

            sportEventsDao.insertSportCategoryWithEvents(
                mergedSportCategoryEntity,
                mergedSportEventEntity
            )
            Result.Success(sportCategoryEntity.map { it.sportId })
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override fun retrieveAllSportEvents(): Flow<List<SportCategory>> {
        return try {
            sportEventsDao.getSportCategoryWithEvents()
                .map { sportCategoryWithEvents ->
                    sportCategoryWithEvents.map {
                        it.toSportCategory()
                    }
                    sportCategoryWithEvents.map { it.toSportCategory() }
                }
        } catch (e: SQLiteException) {
            return flowOf()
        }
    }
}