package com.jinglebroda.domain.usecase.duckUseCase

import com.jinglebroda.domain.model.dataModel.DataDuck
import com.jinglebroda.domain.repository.DomainRepository

class GetAllDucksInLocalDbUseCase(private val repository: DomainRepository) {
    suspend fun doIt():List<DataDuck> = repository.getAllDucks()
}