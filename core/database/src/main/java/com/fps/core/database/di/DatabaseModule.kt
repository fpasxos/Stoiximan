package com.fps.core.database.di

import androidx.room.Room
import com.fps.core.database.LiveEventDatabase
import com.fps.core.database.RoomLocalSportEventsDataSource
import com.fps.core.domain.events.LocalSportEventsDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            LiveEventDatabase::class.java,
            "sportevents.db"
        ).build()
    }
    single { get<LiveEventDatabase>().sportEventsDao }

    singleOf(::RoomLocalSportEventsDataSource).bind<LocalSportEventsDataSource>()
}