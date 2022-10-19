package com.jinglebroda.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jinglebroda.data.local.room.dao.AnimalDao
import com.jinglebroda.data.model.room.entity.CatRoomModel
import com.jinglebroda.data.model.room.entity.DuckRoomModel

@Database(
    entities = [
        CatRoomModel::class,
        DuckRoomModel::class
    ],
    version = 8,
    exportSchema = false
)
abstract class AnimalDataBase():RoomDatabase() {
    abstract fun databaseDao():AnimalDao
}