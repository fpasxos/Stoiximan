package com.fps.stoiximan

import android.app.Application
import com.fps.core.data.di.coreDataModule
import com.fps.core.database.di.databaseModule
import com.fps.events.data.di.eventsDataModule
import com.fps.events.presentation.di.eventsViewModelModule
import com.fps.stoiximan.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class Stoiximan : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@Stoiximan)
            modules(
                eventsDataModule,
                eventsViewModelModule,
                appModule,
                databaseModule,
                coreDataModule
            )
        }
    }
}