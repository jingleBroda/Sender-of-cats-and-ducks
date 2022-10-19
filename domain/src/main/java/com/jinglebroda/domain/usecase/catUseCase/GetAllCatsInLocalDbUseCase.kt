package com.jinglebroda.domain.usecase.catUseCase

import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.repository.DomainRepository

class GetAllCatsInLocalDbUseCase(private val repository: DomainRepository) {
    suspend fun doIt():List<DataCat> = repository.getAllCats()
}