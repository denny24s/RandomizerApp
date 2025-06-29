package com.example.data.storage.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    const val DICE_HISTORY   = "dice_history"
    const val YESNO_HISTORY  = "yesno_history"
    const val NUMBER_HISTORY  = "number_history"
    val LANGUAGE: Preferences.Key<String> = stringPreferencesKey("language_code")
}