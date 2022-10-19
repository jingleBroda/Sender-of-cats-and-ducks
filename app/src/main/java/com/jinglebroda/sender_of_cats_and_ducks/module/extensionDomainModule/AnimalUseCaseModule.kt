package com.jinglebroda.sender_of_cats_and_ducks.module.extensionDomainModule

import com.jinglebroda.domain.repository.DomainRepository
import com.jinglebroda.domain.usecase.animalUseCase.DeleteAnimalUseCase
import com.jinglebroda.domain.usecase.animalUseCase.SaveAnimalUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [
    RepositoryModule::class
])
class AnimalUseCaseModule {
    @Provides
    fun providesSaveAnimalUseCase(repository: DomainRepository) = SaveAnimalUseCase(repository)

    @Provides
    fun providesDeleteAnimal(repository: DomainRepository) = DeleteAnimalUseCase(repository)
}