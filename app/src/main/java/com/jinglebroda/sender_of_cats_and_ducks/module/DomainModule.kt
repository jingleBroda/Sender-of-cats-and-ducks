package com.jinglebroda.sender_of_cats_and_ducks.module

import com.jinglebroda.sender_of_cats_and_ducks.module.extensionDomainModule.AnimalUseCaseModule
import com.jinglebroda.sender_of_cats_and_ducks.module.extensionDomainModule.CatApiUseCaseModule
import com.jinglebroda.sender_of_cats_and_ducks.module.extensionDomainModule.DuckApiUseCaseModule
import com.jinglebroda.sender_of_cats_and_ducks.module.extensionDomainModule.RepositoryModule
import dagger.Module

@Module(includes = [
    RepositoryModule::class,
    CatApiUseCaseModule::class,
    DuckApiUseCaseModule::class,
    AnimalUseCaseModule::class,
])
class DomainModule
