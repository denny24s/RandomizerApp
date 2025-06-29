package com.example.domain.repository

import com.example.domain.models.Language
import kotlinx.coroutines.flow.Flow


interface SettingsRepository {
    val languageFlow: Flow<Language>

    suspend fun setLanguage(lang: Language)
}
