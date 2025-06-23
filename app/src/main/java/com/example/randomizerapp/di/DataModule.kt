package com.example.randomizerapp.di

import com.example.domain.repository.RandomRepository
import com.example.data.repository.RandomRepositoryImpl
import com.example.data.storage.datastore.RandomPrefsDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { RandomPrefsDataStore(androidContext()) }
    single<RandomRepository> { RandomRepositoryImpl(get()) }
}

