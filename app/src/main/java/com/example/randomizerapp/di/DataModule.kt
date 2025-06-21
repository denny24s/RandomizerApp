package com.example.randomizerapp.di

import com.example.domain.repository.RandomRepository
import com.example.domain.repository.RandomRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<RandomRepository> { RandomRepositoryImpl() }
}
