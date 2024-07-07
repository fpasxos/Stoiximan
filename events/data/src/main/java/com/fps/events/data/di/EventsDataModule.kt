package com.fps.events.data.di

import com.fps.core.data.liveevents.LiveEventsRepositoryImpl
import com.fps.core.database.RoomLocalSportEventsDataSource
import com.fps.core.domain.events.EventsRepository
import com.fps.core.domain.events.LocalSportEventsDataSource
import com.fps.core.domain.events.RemoteEventsDataSource
import com.fps.events.data.datasources.KtorRemoteEventsDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val eventsDataModule = module {
    singleOf(::LiveEventsRepositoryImpl).bind<EventsRepository>()
    singleOf(::KtorRemoteEventsDataSource).bind<RemoteEventsDataSource>()
    singleOf(::RoomLocalSportEventsDataSource).bind<LocalSportEventsDataSource>()
}