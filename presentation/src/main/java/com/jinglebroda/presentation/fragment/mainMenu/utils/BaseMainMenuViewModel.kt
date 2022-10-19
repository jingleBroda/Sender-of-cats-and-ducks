package com.jinglebroda.presentation.fragment.mainMenu.utils

import androidx.lifecycle.ViewModel
import com.jinglebroda.domain.model.abstractAnimal.Animal
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck
import kotlinx.coroutines.Job

abstract class BaseMainMenuViewModel:ViewModel() {
    abstract fun getCat(show:(DataCat)->Unit): Job
    abstract fun getDuck(show:(DataDuck)->Unit): Job
    abstract fun likeProcessing(animal: Animal, likeActiveStatus:Boolean)
    abstract fun localSaveCat(dataCat: DataCat)
    abstract fun localSaveDuck(dataDuck: DataDuck)
    abstract fun localDeleteCat(dataCat: DataCat)
    abstract fun localDeleteDuck(dataDuck: DataDuck)
    abstract fun getAnimalId():Long
}