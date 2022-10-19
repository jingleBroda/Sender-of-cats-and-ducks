package com.jinglebroda.domain.usecase.catUseCase

import com.jinglebroda.domain.repository.DomainRepository

class DeleteCatsUseCase(private val repository: DomainRepository) {
    suspend fun doIt() = repository.deleteCats()
}