package com.fps.stoiximan.di

import com.fps.stoiximan.Stoiximan
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single<CoroutineScope> {
        (androidApplication() as Stoiximan).applicationScope
    }
}