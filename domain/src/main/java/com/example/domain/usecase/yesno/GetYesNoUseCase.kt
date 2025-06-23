package com.example.domain.usecase.yesno

import com.example.domain.models.YesNoResult
import com.example.domain.repository.RandomRepository

class GetYesNoUseCase(private val repo: RandomRepository) {
    suspend operator fun invoke(): YesNoResult = repo.getYesNo()
}