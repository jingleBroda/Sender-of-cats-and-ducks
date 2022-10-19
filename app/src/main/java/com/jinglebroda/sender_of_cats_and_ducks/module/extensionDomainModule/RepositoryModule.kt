package com.jinglebroda.sender_of_cats_and_ducks.module.extensionDomainModule

import com.jinglebroda.data.local.room.dao.AnimalDao
import com.jinglebroda.data.repositoryImpl.RepositoryImpl
import com.jinglebroda.data.retrofit.CatRetrofitService
import com.jinglebroda.data.retrofit.DuckRetrofitService
import com.jinglebroda.domain.repository.DomainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providesRepository(
        catRetrofitService: CatRetrofitService,
        duckRetrofitService: DuckRetrofitService,
        dao:AnimalDao
    ):DomainRepository = RepositoryImpl(catRetrofitService, duckRetrofitService, dao)
}