package com.example.domain.usecase.yesno

import com.example.domain.models.YesNoResult
import com.example.domain.repository.RandomRepository
import kotlinx.coroutines.flow.Flow

class GetYesNoHistoryUseCase(private val repo: RandomRepository) {
    operator fun invoke(): Flow<List<YesNoResult>> = repo.yesNoHistory()
}