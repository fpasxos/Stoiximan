@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package com.fps.core.data.di

import com.fps.core.data.liveevents.LiveEventsRepositoryImpl
import com.fps.core.data.networking.ConnectivityManagerImpl
import com.fps.core.data.networking.HttpClientFactory
import com.fps.core.domain.connectivity.ConnectivityManager
import com.fps.core.domain.events.EventsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }

    single<ConnectivityManager> {
        ConnectivityManagerImpl(get())
    }

    singleOf(::LiveEventsRepositoryImpl).bind<EventsRepository>()
}