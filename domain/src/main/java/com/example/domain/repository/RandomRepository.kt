package com.example.domain.repository

import com.example.domain.models.DiceResult
import kotlinx.coroutines.flow.Flow


interface RandomRepository {
    suspend fun rollDice(count: Int): DiceResult            // уже є
    fun diceHistory(): Flow<List<DiceResult>>               // ✅
}

