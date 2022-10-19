package com.jinglebroda.presentation.fragment.closeUpAnimal.utils

import androidx.lifecycle.ViewModel
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck

abstract class BasicCloseUpPictureViewModel: ViewModel() {
    abstract suspend fun deleteCat(dataCat: DataCat, completeLambda:()->Unit)
    abstract suspend fun deleteDuck(dataDuck: DataDuck, completeLambda:()->Unit)
}