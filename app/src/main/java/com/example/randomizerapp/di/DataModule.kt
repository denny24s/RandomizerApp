package com.example.randomizerapp.di

import com.example.data.repository.*
import com.example.data.storage.datastore.*
import com.example.domain.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single { RandomPrefsDataStore(androidContext()) }

    single { androidContext().randomDataStore }

    single<RandomRepository>   { RandomRepositoryImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}
