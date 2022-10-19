package com.jinglebroda.sender_of_cats_and_ducks.module

import com.jinglebroda.sender_of_cats_and_ducks.module.extensionDataModule.RetrofitModule
import com.jinglebroda.sender_of_cats_and_ducks.module.extensionDataModule.RoomModule
import dagger.Module

@Module(includes = [
    RetrofitModule::class,
    RoomModule::class,
])
class DataModule