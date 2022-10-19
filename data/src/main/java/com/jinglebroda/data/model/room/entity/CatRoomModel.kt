package com.jinglebroda.data.model.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jinglebroda.domain.model.dataModel.DataCat

@Entity(
    tableName = "CatsEntity"
)
data class CatRoomModel(
    @PrimaryKey(autoGenerate = true)
    val id:Long?,
    val url:String,
    val webpurl:String,
    val x:Float?,
    val y:Float?,
    val saveData:String = "",
){
    fun transformInCat(): DataCat = DataCat(
        id,
        url,
        webpurl,
        x,
        y,
        saveData
    )

    companion object{
        fun transformCatInCatsEntity(dataCat: DataCat):CatRoomModel = dataCat.run {
            CatRoomModel(
                id,
                url,
                webpurl,
                x,
                y,
                saveData
            )
        }

        fun transformListCatDbInCatDomain(catDbList:List<CatRoomModel>):List<DataCat>{
            val normalDataCat = mutableListOf<DataCat>()
            catDbList.forEach{ catRoom->
                normalDataCat.add(catRoom.transformInCat())
            }
            return normalDataCat
        }
    }
}