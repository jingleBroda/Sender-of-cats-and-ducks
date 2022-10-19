package com.jinglebroda.sender_of_cats_and_ducks.module.extensionDomainModule

import com.jinglebroda.domain.repository.DomainRepository
import com.jinglebroda.domain.usecase.duckUseCase.*
import dagger.Module
import dagger.Provides


@Module(includes = [
    RepositoryModule::class
])
class DuckApiUseCaseModule {
    @Provides
    fun providesGetDuckUseCase(repository: DomainRepository) = GetRandomDuckInNetworkUseCase(repository)

    @Provides
    fun providesGetAllDucksInLocalDb(repository: DomainRepository) = GetAllDucksInLocalDbUseCase(repository)

    @Provides
    fun providesGetConcreteDuckInLocalDb(repository: DomainRepository) = GetConcreteDuckInLocalDbUseCase(repository)

    @Provides
    fun providesLocalSaveDuckUseCase(repository: DomainRepository) = LocalSaveDuckUseCase(repository)

    @Provides
    fun providesLocalDeleteDuckUseCase(repository: DomainRepository) = LocalDeleteDuckUseCase(repository)

    @Provides
    fun providesDeleteDucksUseCase(repository: DomainRepository) = DeleteDucksUseCase(repository)
}