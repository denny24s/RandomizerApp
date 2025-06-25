package com.example.domain.repository

import com.example.domain.models.Language
import kotlinx.coroutines.flow.Flow


interface SettingsRepository {
    /** Поточна мова - «гаряча» Flow, щоб UI автоматично реагував. */
    val languageFlow: Flow<Language>

    /** Зберігаємо вибір користувача. */
    suspend fun setLanguage(lang: Language)
}
