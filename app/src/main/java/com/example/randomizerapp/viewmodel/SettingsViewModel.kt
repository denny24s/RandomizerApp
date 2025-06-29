package com.example.randomizerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.settings.GetLanguageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class SettingsViewModel(
    private val getLang: GetLanguageUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsUiState())

    init {
        viewModelScope.launch {
            getLang().collect { _state.value = SettingsUiState(it) }
        }
    }
}
