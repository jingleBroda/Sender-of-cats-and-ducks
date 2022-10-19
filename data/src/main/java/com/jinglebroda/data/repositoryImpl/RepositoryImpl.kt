package com.jinglebroda.data.repositoryImpl

import com.jinglebroda.data.local.room.dao.AnimalDao
import com.jinglebroda.data.model.room.entity.CatRoomModel
import com.jinglebroda.data.model.room.entity.DuckRoomModel
import com.jinglebroda.data.retrofit.CatRetrofitService
import com.jinglebroda.data.retrofit.DuckRetrofitService
import com.jinglebroda.domain.model.abstractAnimal.Animal
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck
import com.jinglebroda.domain.repository.DomainRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val catRetrofitService: CatRetrofitService,
    private val duckRetrofitService: DuckRetrofitService,
    private val animalDao: AnimalDao
): DomainRepository() {
    override suspend fun getCatAsync(): Deferred<DataCat> = catRetrofitService.getCatAsync()
    override suspend fun getDuckAsync(): Deferred<DataDuck> = duckRetrofitService.getDuckAsync()
    override suspend fun getAllCats(): List<DataCat>  = CatRoomModel.transformListCatDbInCatDomain(
        animalDao.getAllCats()
    )
    override suspend fun getAllDucks(): List<DataDuck> = DuckRoomModel.transformListDuckDbInDuckDomain(
        animalDao.getAllDucks()
    )
    override suspend fun getConcreteCat(dataCat: DataCat): DataCat =
        animalDao.getConcreteCat(dataCat.id!!).transformInCat()

    override suspend fun getConcreteDuck(dataDuck: DataDuck): DataDuck =
        animalDao.getConcreteDuck(dataDuck.url).transformToDuck()

    override suspend fun localSaveCat(dataCat: DataCat): Long = animalDao.saveCat(
        CatRoomModel.transformCatInCatsEntity(dataCat)
    )
    override suspend fun localSaveDuck(dataDuck: DataDuck): Long = animalDao.saveDuck(
        DuckRoomModel.transformDuckToDuckEntity(dataDuck)
    )
    override suspend fun localDeleteCat(dataCat: DataCat) = animalDao.deleteCat(dataCat.id!!)
    override suspend fun localDeleteDuck(dataDuck: DataDuck) = animalDao.deleteDuck(dataDuck.url)
    override suspend fun deleteCats() = animalDao.deleteCats()
    override suspend fun deleteDucks() = animalDao.deleteDucks()

    override suspend fun saveAnimal(animal: Animal) {
        when(animal.getDataAnimal()){
            is DataDuck ->{
                localSaveDuck(animal.getDataAnimal() as DataDuck)
            }

            is DataCat->{
                localSaveCat(animal.getDataAnimal() as DataCat)
            }
        }
    }

    override suspend fun deleteAnimal(animal: Animal) {
        when(animal.getDataAnimal()){
            is DataDuck ->{
                localDeleteDuck(animal.getDataAnimal() as DataDuck)
            }

            is DataCat->{
                localDeleteCat(animal.getDataAnimal() as DataCat)
            }
        }
    }
}