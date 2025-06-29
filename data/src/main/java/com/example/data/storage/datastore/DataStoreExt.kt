package com.example.data.storage.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.randomDataStore by preferencesDataStore(name = "random_prefs")
