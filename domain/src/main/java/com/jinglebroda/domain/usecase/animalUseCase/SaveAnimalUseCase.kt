package com.jinglebroda.domain.usecase.animalUseCase

import com.jinglebroda.domain.model.abstractAnimal.Animal
import com.jinglebroda.domain.repository.DomainRepository

class SaveAnimalUseCase(private val repository: DomainRepository) {
    suspend fun doIt(animal: Animal) = repository.saveAnimal(animal)
}