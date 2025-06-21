package com.example.randomizerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.dice.GetDiceHistoryUseCase
import com.example.domain.usecase.dice.RollDiceUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DiceUiState(
    val faces: List<Int> = emptyList(),
    val history: List<List<Int>> = emptyList(),
    val diceCount: Int = 1
)

class DiceViewModel(
    private val roll: RollDiceUseCase,
    private val historyUC: GetDiceHistoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DiceUiState())
    val state: StateFlow<DiceUiState> = _state.asStateFlow()



    fun onRoll() = viewModelScope.launch {
        val result = roll(_state.value.diceCount)
        _state.update { it.copy(faces = result.faces) }
    }



    fun incDice() = _state.update { it.copy(diceCount = (it.diceCount + 1).coerceAtMost(3)) }
    fun decDice() = _state.update { it.copy(diceCount = (it.diceCount - 1).coerceAtLeast(1)) }
}
