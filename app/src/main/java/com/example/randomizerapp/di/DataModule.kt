// di/DataModule.kt
package com.example.randomizerapp.di

import com.example.data.repository.*
import com.example.data.storage.datastore.*
import com.example.domain.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    /* helper для історій */
    single { RandomPrefsDataStore(androidContext()) }

    /* сам DataStore потрібен SettingsRepository */
    single { androidContext().randomDataStore }

    single<RandomRepository>   { RandomRepositoryImpl(get()) }   // RandomPrefsDataStore
    single<SettingsRepository> { SettingsRepositoryImpl(get()) } // DataStore<Preferences>
}
