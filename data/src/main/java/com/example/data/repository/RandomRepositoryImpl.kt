package com.example.data.repository

import com.example.data.storage.datastore.RandomPrefsDataStore
import com.example.domain.models.DiceResult
import com.example.domain.repository.RandomRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class RandomRepositoryImpl(
    private val prefs: RandomPrefsDataStore
) : RandomRepository {

    private val _historyCache = MutableStateFlow<List<DiceResult>>(emptyList())

    init {
        // синхронізуємося з DataStore один раз
        CoroutineScope(Dispatchers.IO).launch {
            prefs.diceHistory.collect { _historyCache.value = it }
        }
    }

    override fun diceHistory(): Flow<List<DiceResult>> = _historyCache.asStateFlow()

    override suspend fun rollDice(count: Int): DiceResult {
        val faces = List(count.coerceIn(1, 3)) { Random.nextInt(1, 7) }
        val res = DiceResult(faces)
        prefs.saveRoll(res)
        return res
    }

    suspend fun clearDiceHistory() = prefs.clearHistory()
}

