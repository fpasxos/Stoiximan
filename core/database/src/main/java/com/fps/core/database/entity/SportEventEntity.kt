package com.fps.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SportEvent")
data class SportEventEntity (
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val isFavourite: Boolean,
    val sportId: String,
    val fullEventName: String,
    val eventStartTime:Long
)