package com.jinglebroda.data.model.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jinglebroda.domain.model.dataModel.DataDuck

@Entity(
    tableName = "DuckEntity"
)
data class DuckRoomModel(
    @PrimaryKey(autoGenerate = true)
    val id:Long?,
    val url:String,
    val message:String,
    val saveData:String = ""
){
    fun transformToDuck(): DataDuck = DataDuck(
        url,
        message,
        saveData
    )

    companion object{
        fun transformDuckToDuckEntity(dataDuck: DataDuck):DuckRoomModel = dataDuck.run {
            DuckRoomModel(
                null,
                url,
                message,
                saveData
            )
        }

        fun transformListDuckDbInDuckDomain(duckDbList:List<DuckRoomModel>):List<DataDuck>{
            val normalDataDuck = mutableListOf<DataDuck>()
            duckDbList.forEach{ duckRoom->
                normalDataDuck.add(duckRoom.transformToDuck())
            }
            return normalDataDuck
        }
    }
}