package com.example.data.repository

import com.example.data.storage.datastore.RandomPrefsDataStore
import com.example.domain.models.DiceResult
import com.example.domain.models.NumberResult
import com.example.domain.models.YesNoResult
import com.example.domain.repository.RandomRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.random.Random

class RandomRepositoryImpl(
    private val prefs: RandomPrefsDataStore
) : RandomRepository {

    private val yesNoCache = MutableStateFlow<List<YesNoResult>>(emptyList())

    init {
        CoroutineScope(Dispatchers.IO).launch {
            prefs.yesNoHistory.collect { yesNoCache.value = it }
        }
    }

    override suspend fun getYesNo(): YesNoResult {
        val res = YesNoResult(Random.nextBoolean())
        prefs.saveYesNo(res)
        return res
    }

    override fun yesNoHistory(): Flow<List<YesNoResult>> = yesNoCache.asStateFlow()


    private val _historyCache = MutableStateFlow<List<DiceResult>>(emptyList())

    init {
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


    override suspend fun getRandomInt(from: Int, to: Int): NumberResult {
        val result = (from..to).random()
        prefs.appendNumber(result)
        return NumberResult(result)
    }

    override fun observeNumberHistory(limit: Int) =
        prefs.numberHistoryFlow.map { list -> list.takeLast(limit).map { NumberResult(it.value) } }


}

