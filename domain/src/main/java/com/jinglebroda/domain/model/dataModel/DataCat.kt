package com.jinglebroda.domain.model.dataModel

data class DataCat(
    val id:Long?,
    val url:String,
    val webpurl:String,
    val x:Float?,
    val y:Float?,
    val saveData:String = ""
):AbstractDataAnimal(), AbstractSettingDataAnimal<DataCat>{
    override fun addSaveData(data:String): DataCat = DataCat(id, url, webpurl, x, y, data)
    override fun getAnimalUrl(): String = url

    companion object{
        fun getErrorCat(): DataCat = DataCat(
            null,
            "ErrorCat",
            "ErrorCat",
            null,
            null,
        )
    }
}