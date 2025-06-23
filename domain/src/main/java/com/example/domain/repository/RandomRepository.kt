package com.example.domain.repository

import com.example.domain.models.DiceResult
import com.example.domain.models.YesNoResult
import kotlinx.coroutines.flow.Flow


interface RandomRepository {

    /* Dice */
    suspend fun rollDice(count: Int): DiceResult
    fun diceHistory(): Flow<List<DiceResult>>

    /* Yes / No */
    suspend fun getYesNo(): YesNoResult
    fun yesNoHistory(): Flow<List<YesNoResult>>
}


