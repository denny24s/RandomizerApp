package com.example.domain.usecase.number

import com.example.domain.repository.RandomRepository

class GetRandomNumberUseCase(
    private val repo: RandomRepository
) {
    suspend operator fun invoke(from: Int, to: Int) = repo.getRandomInt(from, to)
}