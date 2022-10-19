package com.jinglebroda.domain.model.dataModel

data class DataDuck (
    val url:String,
    val message:String,
    val saveData:String = ""
):AbstractDataAnimal(), AbstractSettingDataAnimal<DataDuck>{
    override fun addSaveData(data:String): DataDuck = DataDuck(url, message, data)
    override fun getAnimalUrl(): String = url

    companion object{
        fun getErrorDuck(): DataDuck = DataDuck(
            "ErrorDuck",
            "ErrorDuck"
        )
    }
}