package com.example.randomizerapp.viewmodel

data class DiceUiState(
    val faces: List<Int> = emptyList(),
    val history: List<List<Int>> = emptyList(),
    val diceCount: Int = 1
)