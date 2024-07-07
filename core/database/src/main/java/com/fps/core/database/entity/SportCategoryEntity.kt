package com.fps.core.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SportCategory")
data class SportCategoryEntity(
    @PrimaryKey(autoGenerate = false)
    val sportId: String,
    val sportName: String,
    val isShowOnlyFavourites: Boolean,
)

