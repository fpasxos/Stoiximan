package com.fps.events.presentation.di

import com.fps.events.presentation.liveevents.LiveEventsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val eventsViewModelModule = module {
    viewModelOf(::LiveEventsViewModel)
}