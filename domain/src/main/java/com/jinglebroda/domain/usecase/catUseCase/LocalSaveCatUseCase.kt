package com.jinglebroda.domain.usecase.catUseCase

import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.repository.DomainRepository

class LocalSaveCatUseCase(private val repository: DomainRepository) {
    suspend fun doIt(dataCat: DataCat):Long = repository.localSaveCat(dataCat)
}