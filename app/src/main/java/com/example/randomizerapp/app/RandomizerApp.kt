package com.example.randomizerapp.app

import android.app.Application
import com.example.randomizerapp.di.dataModule
import com.example.randomizerapp.di.domainModule
import com.example.randomizerapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RandomizerApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RandomizerApp)
            modules(
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}
