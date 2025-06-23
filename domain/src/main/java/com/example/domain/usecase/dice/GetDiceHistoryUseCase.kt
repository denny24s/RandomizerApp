package com.example.domain.usecase.dice

import com.example.domain.models.DiceResult
import com.example.domain.repository.RandomRepository
import kotlinx.coroutines.flow.Flow

class GetDiceHistoryUseCase(private val repo: RandomRepository) {
    operator fun invoke(): Flow<List<DiceResult>> = repo.diceHistory()
}