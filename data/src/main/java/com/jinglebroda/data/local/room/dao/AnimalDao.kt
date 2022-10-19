package com.jinglebroda.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jinglebroda.data.model.room.entity.CatRoomModel
import com.jinglebroda.data.model.room.entity.DuckRoomModel

@Dao
interface AnimalDao {
    //Запросы для котов
    @Query("SELECT * FROM CatsEntity ")
    suspend fun getAllCats():List<CatRoomModel>
    @Query("SELECT * FROM CatsEntity WHERE id=:catId")
    suspend fun getConcreteCat(catId:Long):CatRoomModel
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCat(newCat:CatRoomModel):Long
    @Query("DELETE FROM CatsEntity WHERE id=:catId")
    suspend fun deleteCat(catId:Long)
    @Query("DELETE FROM CatsEntity")
    suspend fun deleteCats()

    //Запросы для уток
    @Query("SELECT * FROM DuckEntity ")
    suspend fun getAllDucks():List<DuckRoomModel>
    @Query("SELECT * FROM DuckEntity WHERE url=:url")
    suspend fun getConcreteDuck(url:String):DuckRoomModel
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDuck(newDuck:DuckRoomModel):Long
    @Query("DELETE FROM DuckEntity WHERE url=:url")
    suspend fun deleteDuck(url:String)
    @Query("DELETE FROM DuckEntity")
    suspend fun deleteDucks()
}