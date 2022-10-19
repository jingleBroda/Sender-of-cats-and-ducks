package com.jinglebroda.domain.model.abstractAnimal

import com.jinglebroda.domain.model.dataModel.AbstractDataAnimal
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck

abstract class Animal{
    abstract fun getErrorAnimal():Animal
    abstract fun addSaveData(data:String)
    abstract fun getDataAnimal():AbstractDataAnimal
}

class AnimalDuck(private var dataDuck: DataDuck):Animal(){

    override fun getErrorAnimal(): Animal = AnimalDuck(
        DataDuck(
            "ErrorDuck",
            "ErrorDuck"
        )
    )

    override fun addSaveData(data: String) {
        dataDuck = dataDuck.addSaveData(data)
    }

    override fun getDataAnimal(): AbstractDataAnimal = dataDuck

}

class AnimalCat(private var dataCat: DataCat):Animal(){

    override fun getErrorAnimal(): Animal = AnimalCat(
        DataCat(
            null,
            "ErrorCat",
            "ErrorCat",
            null,
            null,
        )
    )

    override fun addSaveData(data: String) {
        dataCat = dataCat.addSaveData(data)
    }

    override fun getDataAnimal(): AbstractDataAnimal = dataCat

}