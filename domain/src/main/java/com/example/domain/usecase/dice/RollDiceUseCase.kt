package com.example.domain.usecase.dice

import com.example.domain.repository.RandomRepository

class RollDiceUseCase(private val repo: RandomRepository) {
    suspend operator fun invoke(count: Int) = repo.rollDice(count)
}

class GetDiceHistoryUseCase(private val repo: RandomRepository) {
    operator fun invoke() = repo.diceHistory()
}
