package com.jinglebroda.presentation.fragment.gallery.utils

import androidx.lifecycle.ViewModel
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck
import kotlinx.coroutines.Job

abstract class BaseGalleryViewModel:ViewModel() {
    abstract suspend fun getAllAnimal(handlingAnimalLambda:(List<DataDuck>, List<DataCat>)->Unit)
    abstract fun getAllDucks(): Job
    abstract fun getAllCats():Job
    abstract fun deleteCat(dataCat: DataCat)
    abstract fun deleteDuck(dataDuck: DataDuck)
    abstract suspend fun deleteAllAnimal(onCompleteLambda:()->Unit)
}