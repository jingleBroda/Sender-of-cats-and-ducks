package com.jinglebroda.sender_of_cats_and_ducks.module.extensionDataModule

import android.app.Application
import androidx.room.Room
import com.jinglebroda.data.local.room.dao.AnimalDao
import com.jinglebroda.data.local.room.database.AnimalDataBase
import com.jinglebroda.sender_of_cats_and_ducks.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun createRoomDataBase(daggerApp: App):AnimalDataBase = Room.databaseBuilder(
        daggerApp,
        AnimalDataBase::class.java,
    "AnimalDataBase")
        .fallbackToDestructiveMigration() //TODO возможно позже стоит написаь класс миграции
        .build()

    @Provides
    @Singleton
    fun getDao(db:AnimalDataBase):AnimalDao = db.databaseDao()
}