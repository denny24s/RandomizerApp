package com.example.randomizerapp.ui.main

import android.app.Application
import com.example.data.di.dataModule
import com.example.data.di.domainModule
import com.example.data.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RandomizerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RandomizerApp)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}
