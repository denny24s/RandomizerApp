package com.example.randomizerapp.ui.main

import android.app.Application
import com.example.randomizerapp.di.dataModule
import com.example.randomizerapp.di.domainModule
import com.example.randomizerapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RandomizerApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // ⚡️ Стартуємо Koin ОДИН раз на весь процес
        startKoin {
            androidContext(this@RandomizerApp)
            modules(
                dataModule,       // з модуля :data
                domainModule,     // з модуля :domain
                presentationModule// з модуля :app
            )
        }
    }
}
