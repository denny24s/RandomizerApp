package com.example.randomizerapp.di

import com.example.domain.usecase.dice.GetDiceHistoryUseCase
import com.example.domain.usecase.dice.RollDiceUseCase
import com.example.domain.usecase.number.GetNumberHistoryUseCase
import com.example.domain.usecase.number.GetRandomNumberUseCase
import com.example.domain.usecase.settings.GetLanguageUseCase
import com.example.domain.usecase.settings.SetLanguageUseCase
import com.example.domain.usecase.yesno.GetYesNoHistoryUseCase
import com.example.domain.usecase.yesno.GetYesNoUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { RollDiceUseCase(get()) }
    factory { GetDiceHistoryUseCase(get()) }
    single { GetYesNoUseCase(get()) }
    single { GetYesNoHistoryUseCase(get()) }
    single { GetRandomNumberUseCase(get()) }
    single { GetNumberHistoryUseCase(get()) }

    single { GetLanguageUseCase(get()) }
    single { SetLanguageUseCase(get()) }
}
