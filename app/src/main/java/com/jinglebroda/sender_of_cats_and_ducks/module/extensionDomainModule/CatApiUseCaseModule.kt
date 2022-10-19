package com.jinglebroda.sender_of_cats_and_ducks.module.extensionDomainModule

import com.jinglebroda.domain.repository.DomainRepository
import com.jinglebroda.domain.usecase.catUseCase.*
import dagger.Module
import dagger.Provides

@Module(includes = [
    RepositoryModule::class
])
class CatApiUseCaseModule {
    @Provides
    fun providesGetCatUseCase(repository: DomainRepository) = GetRandomCatInNetworkUseCase(repository)

    @Provides
    fun providesGetAllCatsInLocalDb(repository: DomainRepository) = GetAllCatsInLocalDbUseCase(repository)

    @Provides
    fun providesGetConcreteCatInLocalDb(repository: DomainRepository) = GetConcreteCatInLocalDbUseCase(repository)

    @Provides
    fun providesLocalSaveCatUseCase(repository: DomainRepository) = LocalSaveCatUseCase(repository)

    @Provides
    fun providesLocalDeleteCatUseCase(repository: DomainRepository) = LocalDeleteCatUseCase(repository)

    @Provides
    fun providesDeleteCatsUseCase(repository: DomainRepository) = DeleteCatsUseCase(repository)
}