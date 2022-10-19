package com.jinglebroda.domain.usecase.catUseCase

import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.repository.DomainRepository

class LocalDeleteCatUseCase(private val repository: DomainRepository) {
    suspend fun doIt(dataCat: DataCat) = repository.localDeleteCat(dataCat)
}