package com.jinglebroda.domain.model.dataModel

abstract class AbstractDataAnimal{
    abstract fun getAnimalUrl():String
}

interface AbstractSettingDataAnimal<T>{
    fun addSaveData(data:String):T
}