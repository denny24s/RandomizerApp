package com.example.randomizerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.dice.GetDiceHistoryUseCase
import com.example.domain.usecase.dice.RollDiceUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class DiceViewModel(
    private val rollUC: RollDiceUseCase, getHistoryUC: GetDiceHistoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DiceUiState())
    val state: StateFlow<DiceUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getHistoryUC().collect { list ->
                _state.update { it.copy(history = list.map { dr -> dr.faces }) }
            }
        }
    }

    fun onRoll() = viewModelScope.launch {
        val res = rollUC(_state.value.diceCount)
        _state.update { it.copy(faces = res.faces) }
    }


    fun incDice() = _state.update {
        it.copy(diceCount = (it.diceCount + 1).coerceAtMost(3))
    }
    fun decDice() = _state.update {
        it.copy(diceCount = (it.diceCount - 1).coerceAtLeast(1))
    }
}
