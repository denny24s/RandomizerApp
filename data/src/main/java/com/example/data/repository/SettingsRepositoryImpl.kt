package com.example.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.data.storage.datastore.DataStoreKeys
import com.example.domain.models.Language
import com.example.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(
    private val ds: DataStore<Preferences>
) : SettingsRepository {

    override val languageFlow: Flow<Language> = ds.data.map { prefs ->
        val code = prefs[DataStoreKeys.LANGUAGE] ?: Language.EN.code
        Language.fromCode(code)
    }

    override suspend fun setLanguage(lang: Language) {
        ds.edit { it[DataStoreKeys.LANGUAGE] = lang.code }
    }
}
