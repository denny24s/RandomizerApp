package com.example.randomizerapp.di

import com.example.randomizerapp.viewmodel.DiceViewModel
import com.example.randomizerapp.viewmodel.NumberViewModel
import com.example.randomizerapp.viewmodel.YesNoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { DiceViewModel(get(), get()) }
    viewModel { YesNoViewModel(get(), get()) }
    viewModel { NumberViewModel(get(), get()) }
}

