package com.jinglebroda.domain.usecase.duckUseCase

import com.jinglebroda.domain.model.dataModel.DataDuck
import com.jinglebroda.domain.repository.DomainRepository

class LocalDeleteDuckUseCase(private val repository: DomainRepository) {
    suspend fun doIt(dataDuck: DataDuck) = repository.localDeleteDuck(dataDuck)
}