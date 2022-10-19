package com.jinglebroda.presentation.fragment.gallery

import androidx.lifecycle.viewModelScope
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck
import com.jinglebroda.domain.usecase.catUseCase.DeleteCatsUseCase
import com.jinglebroda.domain.usecase.catUseCase.GetAllCatsInLocalDbUseCase
import com.jinglebroda.domain.usecase.catUseCase.LocalDeleteCatUseCase
import com.jinglebroda.domain.usecase.duckUseCase.DeleteDucksUseCase
import com.jinglebroda.domain.usecase.duckUseCase.GetAllDucksInLocalDbUseCase
import com.jinglebroda.domain.usecase.duckUseCase.LocalDeleteDuckUseCase
import com.jinglebroda.presentation.fragment.gallery.utils.BaseGalleryViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class GalleryViewModel @Inject constructor(
    private val getAllDucksInLocalDbUseCase: GetAllDucksInLocalDbUseCase,
    private val getAllCatsInLocalDbUseCase: GetAllCatsInLocalDbUseCase,
    private val deleteCatUseCase: LocalDeleteCatUseCase,
    private val deleteDuckUseCase: LocalDeleteDuckUseCase,
    private val deleteCatsUseCase: DeleteCatsUseCase,
    private val deleteDucksUseCase: DeleteDucksUseCase
): BaseGalleryViewModel() {
    private val _ducksFlow = MutableStateFlow<List<DataDuck>>(listOf())
    private val _catsFlow = MutableStateFlow<List<DataCat>>(listOf())

    override suspend fun getAllAnimal(handlingAnimalLambda: (List<DataDuck>, List<DataCat>) -> Unit) {
        getAllDucks()
        getAllCats()
        return suspendCoroutine {
            viewModelScope.launch(Dispatchers.Main) {
                combine(_ducksFlow, _catsFlow) { ducks, cats ->
                    handlingAnimalLambda.invoke(ducks, cats)
                }.collect()
            }
        }
    }

    override fun getAllDucks():Job = viewModelScope.launch(Dispatchers.Default){
        _ducksFlow.emit(getAllDucksInLocalDbUseCase.doIt())
    }

    override fun getAllCats():Job = viewModelScope.launch(Dispatchers.Default) {
        _catsFlow.emit(getAllCatsInLocalDbUseCase.doIt())
    }

    override fun deleteCat(dataCat: DataCat) {
        viewModelScope.launch(Dispatchers.Default) {
            deleteCatUseCase.doIt(dataCat)
        }
    }

    override fun deleteDuck(dataDuck: DataDuck) {
        viewModelScope.launch(Dispatchers.Default) {
            deleteDuckUseCase.doIt(dataDuck)
        }
    }

    override suspend fun deleteAllAnimal(onCompleteLambda:()->Unit) {
        viewModelScope.launch(Dispatchers.Default) {
            deleteCatsUseCase.doIt()
            deleteDucksUseCase.doIt()
        }
        return suspendCoroutine {
            onCompleteLambda.invoke()
        }
    }
}