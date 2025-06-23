package com.example.domain.usecase.dice

import com.example.domain.models.DiceResult
import com.example.domain.repository.RandomRepository

class RollDiceUseCase(private val repo: RandomRepository) {
    /** Кидаємо, зберігаємо у репо й повертаємо результат. */
    suspend operator fun invoke(count: Int): DiceResult = repo.rollDice(count)
}
