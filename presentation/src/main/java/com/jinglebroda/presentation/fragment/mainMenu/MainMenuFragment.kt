package com.jinglebroda.presentation.fragment.mainMenu

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.jinglebroda.domain.model.abstractAnimal.AnimalCat
import com.jinglebroda.domain.model.abstractAnimal.AnimalDuck
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.MainMenuLayoutBinding
import com.jinglebroda.presentation.fragment.gallery.GalleryFragment
import com.jinglebroda.presentation.fragment.mainMenu.utils.*
import com.jinglebroda.presentation.navigator.navigator
import com.jinglebroda.presentation.utils.factory.viewModelFactory.ViewModelFactory
import com.jinglebroda.presentation.utils.parcelizeAnimal.CatParcelize
import com.jinglebroda.presentation.utils.parcelizeAnimal.DuckParcelize
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.Job
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainMenuFragment : DaggerFragment(R.layout.main_menu_layout), View.OnClickListener {

    private lateinit var binding:MainMenuLayoutBinding
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel:MainMenuViewModel
    private var activeJob: Job? = null
    private lateinit var handlerAnimal:HandlerShowAnimal.Base
    private lateinit var toastErrorCallBack:HandlerError.Base
    private lateinit var handlerGenerateCats:HandlerGenerateButtons.DefaultHandlerGenerateButtons
    private lateinit var handlerGenerateDucks:HandlerGenerateButtons.DefaultHandlerGenerateButtons
    private var handlerAnimalImageAnimal: HandlerImageAnimalMainMenu.Base? = null
    private lateinit var handlerLike:HandlerLike.Base
    private lateinit var handlerSaveStateAnimal: HandlerSaveStateAnimal.Base
    private val handlerActualAnimalModel = HandlerActualAnimalModel.Base()
    private var actualUrl:String =""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = MainMenuLayoutBinding.bind(view)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainMenuViewModel::class.java]
        toastErrorCallBack = HandlerError.Base(requireContext())
        handlerGenerateCats = HandlerGenerateButtons.DefaultHandlerGenerateButtons(
            binding.generateCat
        )
        handlerGenerateDucks = HandlerGenerateButtons.DefaultHandlerGenerateButtons(
            binding.generateDuck
        )
        handlerLike =HandlerLike.Base(
            binding.saveAnimal
        )
        handlerAnimalImageAnimal = HandlerImageAnimalMainMenu.Base(
            requireContext(),
            binding.progressGenerateAnimal,
            binding.animalPictureMainMenu,
            actualUrl,
            handlerLike,
            object :RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    if(e != null){
                        toastErrorCallBack.toastError(e)
                    }
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    handlerAnimalImageAnimal?.setDownloadHiding(true)
                    handlerGenerateCats.setActiveStatus(true)
                    handlerGenerateDucks.setActiveStatus(true)
                    return false
                }
            }
        )
        handlerAnimal = HandlerShowAnimal.Base(
            toastErrorCallBack,
            handlerGenerateCats,
            handlerGenerateDucks,
            handlerAnimalImageAnimal!!,
            handlerActualAnimalModel
        )
        handlerSaveStateAnimal = HandlerSaveStateAnimal.Base(
            handlerAnimalImageAnimal!!
        )

        with(binding){
            openCollection.setOnClickListener(this@MainMenuFragment)
            generateDuck.setOnClickListener(this@MainMenuFragment)
            generateCat.setOnClickListener(this@MainMenuFragment)
            exitMainMenu.setOnClickListener(this@MainMenuFragment)
            saveAnimal.setOnClickListener(this@MainMenuFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.openCollection ->{
                activeJob?.cancel()
                navigator().showNextScreen(GalleryFragment())
            }
            R.id.generateDuck ->{
                handlerAnimal.setVisibilityStatusAnimal(false)
                activeJob = viewModel.getDuck{ dataDuck->
                    handlerAnimal.show(AnimalDuck(dataDuck))
                }
            }
            R.id.generateCat ->{
                handlerAnimal.setVisibilityStatusAnimal(false)
                activeJob = viewModel.getCat{ dataCat->
                    handlerAnimal.show(AnimalCat(dataCat))
                }
            }
            R.id.exitMainMenu ->{
                activeJob?.cancel()
                navigator().back()
            }
            R.id.saveAnimal->{
                val sdf = SimpleDateFormat("dd.MM.yyyy")
                val currentDate = sdf.format(Date())
                val saveAnimal = handlerActualAnimalModel.getActualAnimalModel()
                saveAnimal!!.addSaveData(currentDate)
                viewModel.likeProcessing(
                    saveAnimal,
                    binding.saveAnimal.isChecked
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if(savedInstanceState != null){
            val actualAnimal: Parcelable? = savedInstanceState.getParcelable<CatParcelize>(
                ACTUAL_ANIMAL_KEY
            )
            when(actualAnimal){
                is CatParcelize->{
                    actualUrl = actualAnimal.toCat().url
                    handlerActualAnimalModel.setActualAnimalModel(
                        AnimalCat(
                            actualAnimal.toCat()
                        )
                    )

                }

                is DuckParcelize->{
                    actualUrl = actualAnimal.toDuck().url
                    handlerActualAnimalModel.setActualAnimalModel(
                        AnimalDuck(
                            actualAnimal.toDuck()
                        )
                    )

                }
            }
        }
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if(handlerActualAnimalModel.getActualAnimalModel() != null){
            when(handlerActualAnimalModel.getActualAnimalModel()){
                is AnimalCat->{
                    val dataCat = (
                            handlerActualAnimalModel.getActualAnimalModel() as AnimalCat
                            ).getDataAnimal() as DataCat
                    outState.putParcelable(
                        ACTUAL_ANIMAL_KEY,
                        CatParcelize(
                            dataCat.id,
                            dataCat.url,
                            dataCat.webpurl,
                            dataCat.x,
                            dataCat.y,
                        )
                    )
                }
                is AnimalDuck ->{
                    val dataDuck = (
                            handlerActualAnimalModel.getActualAnimalModel() as AnimalDuck
                            ).getDataAnimal() as DataDuck
                    outState.putParcelable(
                        ACTUAL_ANIMAL_KEY,
                        DuckParcelize(
                            dataDuck.url,
                            dataDuck.message,
                        )
                    )
                }
            }
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        activeJob?.cancel()
        super.onDestroy()
    }

    companion object{
        private const val ACTUAL_ANIMAL_KEY = "ACTUAL_ANIMAL_KEY"
    }
}