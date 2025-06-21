package com.example.randomizerapp.di

import com.example.domain.usecase.dice.GetDiceHistoryUseCase
import com.example.domain.usecase.dice.RollDiceUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { RollDiceUseCase(get()) }
    factory { GetDiceHistoryUseCase(get()) }
}
