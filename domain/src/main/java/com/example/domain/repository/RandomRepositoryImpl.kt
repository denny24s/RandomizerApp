package com.example.domain.repository

import com.example.domain.models.DiceResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class RandomRepositoryImpl : RandomRepository {

    /** Триматимемо історію в пам'яті – пізніше замінимо на DataStore. */
    private val _history = MutableStateFlow<List<DiceResult>>(emptyList())

    override suspend fun rollDice(count: Int): DiceResult {
        val faces = List(count.coerceIn(1, 3)) { Random.nextInt(1, 7) }
        val result = DiceResult(faces)

        // оновлюємо історію
        _history.value = (listOf(result) + _history.value).take(10)
        return result
    }

    override fun diceHistory(): Flow<List<DiceResult>> = _history.asStateFlow()
}
