package com.fps.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.fps.core.database.entity.SportEventEntity
import com.fps.core.database.entity.SportCategoryEntity
import com.fps.core.database.entity.SportCategoryWithEvents
import kotlinx.coroutines.flow.Flow

@Dao
interface SportEventsDao {

    @Upsert
    suspend fun insertSportCategory(sportCategoryEntity: List<SportCategoryEntity>)

    @Upsert
    suspend fun insertLiveSportEventEntity(sportEventEntity: List<SportEventEntity>)

    @Transaction
    suspend fun insertSportCategoryWithEvents(sportCategoryEntities: List<SportCategoryEntity>, liveSportEventEntities: List<SportEventEntity>) {
        insertSportCategory(sportCategoryEntities)
        insertLiveSportEventEntity(liveSportEventEntities)
    }

    @Query("SELECT * FROM sportevent WHERE isFavourite = 1")
    suspend fun getFavouriteSportEvents(): List<SportEventEntity>

    @Query("SELECT * FROM sportcategory WHERE isShowOnlyFavourites = 1")
    suspend fun getFavouriteSportCategories(): List<SportCategoryEntity>

    @Query("Update sportevent SET isFavourite = :setIsFavourite WHERE id = :sportId")
    suspend fun saveSportsEventAsFavourite(sportId: String, setIsFavourite: Boolean)

    @Query("Update sportcategory SET isShowOnlyFavourites = :showFavouritesOnlyFromCategory WHERE sportId = :categoryId")
    suspend fun saveShowSportCategoryFavouritesOnly(categoryId: String, showFavouritesOnlyFromCategory: Boolean)

    @Query("Select * from sportcategory ORDER BY sportId DESC")
    fun getSportCategoryWithEvents(): Flow<List<SportCategoryWithEvents>>
}