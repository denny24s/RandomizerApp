package com.example.data.di

import com.example.domain.usecase.dice.GetDiceHistoryUseCase
import com.example.domain.usecase.dice.RollDiceUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { RollDiceUseCase(get()) }
    factory { GetDiceHistoryUseCase(get()) }
}
