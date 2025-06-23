package com.example.randomizerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.yesno.GetYesNoHistoryUseCase
import com.example.domain.usecase.yesno.GetYesNoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class YesNoViewModel(
    private val getAnswerUC: GetYesNoUseCase,
    getHistoryUC: GetYesNoHistoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(YesNoUiState())
    val state: StateFlow<YesNoUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getHistoryUC().collect { list ->
                _state.update { it.copy(history = list) }
            }
        }
    }

    fun onGet() = viewModelScope.launch {
        val res = getAnswerUC()
        _state.update { it.copy(answer = res) }
    }
}