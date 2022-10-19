package com.jinglebroda.domain.repository

import com.jinglebroda.domain.model.abstractAnimal.Animal
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck
import kotlinx.coroutines.Deferred

abstract class DomainRepository {
    abstract suspend fun getCatAsync(): Deferred<DataCat>
    abstract suspend fun getDuckAsync(): Deferred<DataDuck>
    abstract suspend fun getAllCats():List<DataCat>
    abstract suspend fun getAllDucks():List<DataDuck>
    abstract suspend fun getConcreteCat(dataCat: DataCat): DataCat
    abstract suspend fun getConcreteDuck(dataDuck: DataDuck): DataDuck
    abstract suspend fun localSaveCat(dataCat: DataCat):Long
    abstract suspend fun localSaveDuck(dataDuck: DataDuck):Long
    abstract suspend fun localDeleteCat(dataCat: DataCat)
    abstract suspend fun localDeleteDuck(dataDuck: DataDuck)
    abstract suspend fun deleteCats()
    abstract suspend fun deleteDucks()

    //
    abstract suspend fun saveAnimal(animal: Animal)
    abstract suspend fun deleteAnimal(animal: Animal)
}