package com.example.data.storage.datastore


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

import com.example.data.storage.datastore.DataStoreKeys.DICE_HISTORY
import com.example.domain.models.DiceResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "random_prefs")
private val gson = Gson()
private val keyDiceHistory = stringPreferencesKey(DICE_HISTORY)

/** API, щоб репозиторій міг читати/писати prefs */
class RandomPrefsDataStore(private val context: Context) {

    /** повертає Flow<List<DiceResult>> */
    val diceHistory: Flow<List<DiceResult>> = context.dataStore.data.map { prefs ->
        prefs[keyDiceHistory]
            ?.let { json ->
                gson.fromJson(json, Array<DiceResult>::class.java)?.toList()
            } ?: emptyList()
    }

    /** оновлюємо історію (додаємо новий roll → обрізаємо до 10) */
    suspend fun saveRoll(result: DiceResult) {
        context.dataStore.edit { prefs ->
            val current = prefs[keyDiceHistory]
                ?.let { gson.fromJson(it, Array<DiceResult>::class.java)?.toMutableList() }
                ?: mutableListOf()
            current.add(0, result)            // новий угорі
            val trimmed = current.take(10)
            prefs[keyDiceHistory] = gson.toJson(trimmed)
        }
    }

    suspend fun clearHistory() {
        context.dataStore.edit { it.remove(keyDiceHistory) }
    }
}
