package com.example.randomizerapp.di

import com.example.randomizerapp.viewmodel.DiceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { DiceViewModel(get(), get()) }
}
