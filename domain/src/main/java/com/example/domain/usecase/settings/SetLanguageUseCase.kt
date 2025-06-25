package com.example.domain.usecase.settings

import com.example.domain.models.Language
import com.example.domain.repository.SettingsRepository


class SetLanguageUseCase(private val repo: SettingsRepository) {
    suspend operator fun invoke(lang: Language) = repo.setLanguage(lang)
}