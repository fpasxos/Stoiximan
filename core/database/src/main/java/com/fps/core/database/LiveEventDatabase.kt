package com.fps.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fps.core.database.dao.SportEventsDao
import com.fps.core.database.entity.SportEventEntity
import com.fps.core.database.entity.SportCategoryEntity

@Database(
    entities = [
        SportCategoryEntity::class,
        SportEventEntity::class
    ],
    version = 1
)

abstract class LiveEventDatabase : RoomDatabase() {

    abstract val sportEventsDao: SportEventsDao
}