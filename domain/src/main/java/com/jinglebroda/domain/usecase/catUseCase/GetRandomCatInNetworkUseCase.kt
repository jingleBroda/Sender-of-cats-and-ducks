package com.jinglebroda.domain.usecase.catUseCase

import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.repository.DomainRepository
import kotlinx.coroutines.Deferred

class GetRandomCatInNetworkUseCase(private val domainRepository: DomainRepository) {
    suspend fun doIt(): Deferred<DataCat> = domainRepository.getCatAsync()
}