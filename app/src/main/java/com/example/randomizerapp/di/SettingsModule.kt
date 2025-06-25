package com.example.randomizerapp.di


import com.example.randomizerapp.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    viewModel { SettingsViewModel(get(), get()) }   // поки що отримає 2 use-cases
}
