package com.example.data.storage.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.domain.models.*
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RandomPrefsDataStore(private val context: Context) {

    private val ds = context.randomDataStore
    private val gson = Gson()

    /* ---------- keys ---------- */
    private val keyDiceHistory   = stringPreferencesKey(DataStoreKeys.DICE_HISTORY)
    private val keyYesNoHistory  = stringPreferencesKey(DataStoreKeys.YESNO_HISTORY)
    private val keyNumberHistory = stringPreferencesKey(DataStoreKeys.NUMBER_HISTORY)

    /* ---------- READ ---------- */
    val diceHistory:  Flow<List<DiceResult>>   = ds.data.map { prefs ->
        prefs[keyDiceHistory]
            ?.let { gson.fromJson(it, Array<DiceResult>::class.java)?.toList() }
            ?: emptyList()
    }

    val yesNoHistory: Flow<List<YesNoResult>>  = ds.data.map { prefs ->
        prefs[keyYesNoHistory]
            ?.let { gson.fromJson(it, Array<YesNoResult>::class.java)?.toList() }
            ?: emptyList()
    }

    val numberHistoryFlow: Flow<List<StoredNumber>> = ds.data.map { prefs ->
        prefs[keyNumberHistory]
            ?.let { gson.fromJson(it, Array<StoredNumber>::class.java)?.toList() }
            ?: emptyList()
    }

    /* ---------- WRITE ---------- */
    suspend fun saveRoll(res: DiceResult) = ds.edit { p ->
        val cur = p[keyDiceHistory]?.let {
            gson.fromJson(it, Array<DiceResult>::class.java)?.toMutableList()
        } ?: mutableListOf()
        cur.add(0, res)
        p[keyDiceHistory] = gson.toJson(cur.take(10))
    }

    suspend fun saveYesNo(res: YesNoResult) = ds.edit { p ->
        val cur = p[keyYesNoHistory]?.let {
            gson.fromJson(it, Array<YesNoResult>::class.java)?.toMutableList()
        } ?: mutableListOf()
        cur.add(0, res)
        p[keyYesNoHistory] = gson.toJson(cur.take(10))
    }

    suspend fun appendNumber(n: Int) = ds.edit { p ->
        val cur = p[keyNumberHistory]?.let {
            gson.fromJson(it, Array<StoredNumber>::class.java)?.toMutableList()
        } ?: mutableListOf()
        cur.add(StoredNumber(n))
        p[keyNumberHistory] = gson.toJson(cur.takeLast(10))
    }

    suspend fun clearHistories() = ds.edit { it.clear() }
}

data class StoredNumber(val value: Int)
