package com.example.domain.usecase.settings

import com.example.domain.repository.SettingsRepository

class GetLanguageUseCase(private val repo: SettingsRepository) {
    operator fun invoke() = repo.languageFlow
}