package com.jinglebroda.domain.usecase.duckUseCase

import com.jinglebroda.domain.repository.DomainRepository

class DeleteDucksUseCase(private val repository: DomainRepository) {
    suspend fun doIt() = repository.deleteDucks()
}