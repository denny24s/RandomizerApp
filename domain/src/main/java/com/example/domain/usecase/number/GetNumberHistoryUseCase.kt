package com.example.domain.usecase.number

import com.example.domain.repository.RandomRepository

class GetNumberHistoryUseCase(
    private val repo: RandomRepository
) {
    operator fun invoke(limit: Int = 10) = repo.observeNumberHistory(limit)
}