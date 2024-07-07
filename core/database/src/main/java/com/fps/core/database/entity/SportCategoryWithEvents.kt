package com.fps.core.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class SportCategoryWithEvents(
    @Embedded val sportCategory: SportCategoryEntity,
    @Relation(
        parentColumn = "sportId",
        entityColumn = "sportId"
    )
    val activeSportEvents: List<SportEventEntity>
)
