package com.example.randomizerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.number.GetNumberHistoryUseCase
import com.example.domain.usecase.number.GetRandomNumberUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NumberViewModel(
    private val getRandom: GetRandomNumberUseCase,
    private val historyUC: GetNumberHistoryUseCase
) : ViewModel() {

    data class UiState(
        val number: Int? = null,
        val history: List<Int> = emptyList(),
        val from: String = "0",
        val to: String = "100"
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            historyUC().collect { list ->
                _state.update { it.copy(history = list.map { r -> r.value }) }
            }
        }
    }

    fun updateFrom(text: String) = _state.update {
        it.copy(from = text.filter { ch -> ch.isDigit() })
    }

    fun updateTo(text: String) = _state.update {
        it.copy(to = text.filter { ch -> ch.isDigit() })
    }

    fun onGenerate() = viewModelScope.launch {
        val from = _state.value.from.toIntOrNull() ?: return@launch
        val to = _state.value.to.toIntOrNull() ?: return@launch
        if (from > to) return@launch
        val res = getRandom(from, to)
        _state.update { it.copy(number = res.value) }
    }
}
