package com.jinglebroda.domain.usecase.catUseCase

import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.repository.DomainRepository

class GetConcreteCatInLocalDbUseCase(private val repository: DomainRepository) {
    suspend fun doIt(dataCat: DataCat): DataCat = repository.getConcreteCat(dataCat)
}