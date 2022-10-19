package com.jinglebroda.presentation.fragment.closeUpAnimal

import androidx.lifecycle.viewModelScope
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck
import com.jinglebroda.domain.usecase.catUseCase.LocalDeleteCatUseCase
import com.jinglebroda.domain.usecase.duckUseCase.LocalDeleteDuckUseCase
import com.jinglebroda.presentation.fragment.closeUpAnimal.utils.BasicCloseUpPictureViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class CloseUpPictureViewModel @Inject constructor(
    private val localDeleteCatUseCase: LocalDeleteCatUseCase,
    private val localDeleteDuckUseCase: LocalDeleteDuckUseCase
): BasicCloseUpPictureViewModel() {
    override suspend fun deleteCat(dataCat: DataCat, completeLambda: () -> Unit) {
        viewModelScope.launch(Dispatchers.Default) {
            localDeleteCatUseCase.doIt(dataCat)
        }
        return suspendCoroutine {
            completeLambda.invoke()
        }
    }

    override suspend fun deleteDuck(dataDuck: DataDuck, completeLambda: () -> Unit) {
        viewModelScope.launch(Dispatchers.Default) {
            localDeleteDuckUseCase.doIt(dataDuck)
        }
        return suspendCoroutine {
            completeLambda.invoke()
        }
    }
}