package com.example.randomizerapp.di

import com.example.domain.usecase.dice.GetDiceHistoryUseCase
import com.example.domain.usecase.dice.RollDiceUseCase
import com.example.domain.usecase.yesno.GetYesNoHistoryUseCase
import com.example.domain.usecase.yesno.GetYesNoUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { RollDiceUseCase(get()) }
    factory { GetDiceHistoryUseCase(get()) }
    single { GetYesNoUseCase(get()) }
    single { GetYesNoHistoryUseCase(get()) }
}
