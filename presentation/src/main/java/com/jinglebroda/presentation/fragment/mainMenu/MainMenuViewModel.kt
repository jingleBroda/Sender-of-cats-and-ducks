package com.jinglebroda.presentation.fragment.mainMenu

import androidx.lifecycle.viewModelScope
import com.jinglebroda.domain.model.abstractAnimal.Animal
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck
import com.jinglebroda.domain.usecase.animalUseCase.DeleteAnimalUseCase
import com.jinglebroda.domain.usecase.animalUseCase.SaveAnimalUseCase
import com.jinglebroda.domain.usecase.catUseCase.GetRandomCatInNetworkUseCase
import com.jinglebroda.domain.usecase.catUseCase.LocalDeleteCatUseCase
import com.jinglebroda.domain.usecase.catUseCase.LocalSaveCatUseCase
import com.jinglebroda.domain.usecase.duckUseCase.GetRandomDuckInNetworkUseCase
import com.jinglebroda.domain.usecase.duckUseCase.LocalDeleteDuckUseCase
import com.jinglebroda.domain.usecase.duckUseCase.LocalSaveDuckUseCase
import com.jinglebroda.presentation.fragment.mainMenu.utils.BaseMainMenuViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(
    private val getRandomCatInNetworkUseCase: GetRandomCatInNetworkUseCase,
    private val getRandomDuckInNetworkUseCase: GetRandomDuckInNetworkUseCase,
    private val saveDuckUseCase: LocalSaveDuckUseCase,
    private val saveCatUseCase: LocalSaveCatUseCase,
    private val deleteCatUseCase: LocalDeleteCatUseCase,
    private val deleteDuckUseCase: LocalDeleteDuckUseCase,
    private val saveAnimalUseCase: SaveAnimalUseCase,
    private val deleteAnimalUseCase: DeleteAnimalUseCase
): BaseMainMenuViewModel(){
    private var lastAddedAnimalId:Long = -1L

    override fun getCat(show:(DataCat)->Unit):Job = viewModelScope.launch(Dispatchers.IO) {
        try{
            val result = getRandomCatInNetworkUseCase.doIt().await()
            withContext(Dispatchers.Main){
                show.invoke(result)
            }
        }
        catch(e:Exception){
            withContext(Dispatchers.Main){
                show.invoke(DataCat.getErrorCat())
            }
        }
    }

    override fun getDuck(show:(DataDuck)->Unit):Job = viewModelScope.launch(Dispatchers.IO) {
        try{
            val result = getRandomDuckInNetworkUseCase.doIt().await()
            withContext(Dispatchers.Main){
               show.invoke(result)
            }
        }
        catch(e:Exception){
            withContext(Dispatchers.Main){
                show.invoke(DataDuck.getErrorDuck())
            }
        }
    }

    override fun likeProcessing(animal: Animal, likeActiveStatus: Boolean) {
        viewModelScope.launch(Dispatchers.Default) {
            if (likeActiveStatus) {
                saveAnimalUseCase.doIt(animal)
            }
            else{
                deleteAnimalUseCase.doIt(animal)
            }
        }
    }

    override fun localSaveCat(dataCat: DataCat) {
        viewModelScope.launch(Dispatchers.Default) {
            lastAddedAnimalId = saveCatUseCase.doIt(dataCat)
        }
    }

    override fun localSaveDuck(dataDuck: DataDuck) {
        viewModelScope.launch(Dispatchers.Default) {
            lastAddedAnimalId = saveDuckUseCase.doIt(dataDuck)
        }
    }

    override fun localDeleteCat(dataCat: DataCat) {
        viewModelScope.launch(Dispatchers.Default) {
            deleteCatUseCase.doIt(dataCat)
        }
    }

    override fun localDeleteDuck(dataDuck: DataDuck) {
        viewModelScope.launch(Dispatchers.Default) {
            deleteDuckUseCase.doIt(dataDuck)
        }
    }

    override fun getAnimalId(): Long = lastAddedAnimalId
}