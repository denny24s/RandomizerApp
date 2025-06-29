package com.example.randomizerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.settings.GetLanguageUseCase
import com.example.domain.usecase.settings.SetLanguageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SettingsViewModel(
    private val getLang: GetLanguageUseCase,
    private val setLang: SetLanguageUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsUiState())
    val state: StateFlow<SettingsUiState> = _state

    init {
        viewModelScope.launch {
            getLang().collect { _state.value = SettingsUiState(it) }
        }
    }
}
