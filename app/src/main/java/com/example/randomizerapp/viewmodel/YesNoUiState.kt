package com.example.randomizerapp.viewmodel

import com.example.domain.models.YesNoResult

data class YesNoUiState(
    val answer: YesNoResult? = null,
    val history: List<YesNoResult> = emptyList()
)
