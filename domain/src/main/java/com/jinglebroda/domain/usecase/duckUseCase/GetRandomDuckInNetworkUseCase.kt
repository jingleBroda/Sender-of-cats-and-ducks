package com.jinglebroda.domain.usecase.duckUseCase

import com.jinglebroda.domain.model.dataModel.DataDuck
import com.jinglebroda.domain.repository.DomainRepository
import kotlinx.coroutines.Deferred

class GetRandomDuckInNetworkUseCase(private val domainRepository: DomainRepository) {
    suspend fun doIt(): Deferred<DataDuck> = domainRepository.getDuckAsync()
}