package com.example.domain.usecase.dice

import com.example.domain.models.DiceResult
import com.example.domain.repository.RandomRepository

class RollDiceUseCase(private val repo: RandomRepository) {
    suspend operator fun invoke(count: Int): DiceResult = repo.rollDice(count)
}
