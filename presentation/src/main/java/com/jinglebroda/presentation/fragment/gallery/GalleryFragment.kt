package com.jinglebroda.presentation.fragment.gallery

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.GalleryLayoutBinding
import com.jinglebroda.presentation.fragment.closeUpAnimal.CloseUpPictureFragment
import com.jinglebroda.presentation.fragment.closeUpAnimal.CloseUpPictureFragmentArgs
import com.jinglebroda.presentation.fragment.gallery.adapter.GalleryRecViewAdapter
import com.jinglebroda.presentation.fragment.gallery.adapter.adapterModel.GalleryAdapterCatModel
import com.jinglebroda.presentation.fragment.gallery.adapter.adapterModel.GalleryAdapterDuckModel
import com.jinglebroda.presentation.fragment.gallery.utils.HandlerVisibilityGalleryView
import com.jinglebroda.presentation.fragment.gallery.utils.InitializerGalleryRecView
import com.jinglebroda.presentation.fragment.gallery.utils.UrlAnimalToNameAnimalParser
import com.jinglebroda.presentation.navigator.navigator
import com.jinglebroda.presentation.utils.extensionFunFragment.sizeDisplayInPx
import com.jinglebroda.presentation.utils.factory.viewModelFactory.ViewModelFactory
import com.jinglebroda.presentation.utils.glide.GlideApp
import com.jinglebroda.presentation.utils.parcelizeAnimal.CatParcelize
import com.jinglebroda.presentation.utils.parcelizeAnimal.DuckParcelize
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GalleryFragment: DaggerFragment(R.layout.gallery_layout),View.OnClickListener {

    private lateinit var binding:GalleryLayoutBinding
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: GalleryViewModel
    private lateinit var handlerGalleryRecView: InitializerGalleryRecView.Base
    private lateinit var handlerVisibilityGalleryView: HandlerVisibilityGalleryView.Base

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = GalleryLayoutBinding.bind(view)
        viewModel = ViewModelProvider(this, viewModelFactory)[GalleryViewModel::class.java]
        with(binding){
            handlerGalleryRecView = InitializerGalleryRecView.Base(
                requireContext(),
                galleryRecyclerView
            )
            handlerVisibilityGalleryView = HandlerVisibilityGalleryView.Base(
                galleryRecyclerView,
                deleteAllGalleryAnimal,
                warningEmptyAnimalList
            )
            deleteAllGalleryAnimal.setOnClickListener(this@GalleryFragment)

            lifecycleScope.launch(Dispatchers.Main){
                viewModel.getAllAnimal{ ducks, cats ->
                    if((ducks.isNotEmpty())||(cats.isNotEmpty())){
                        handlerVisibilityGalleryView.showRecyclerStrategy(true)
                        handlerGalleryRecView.initRecView(
                            cats,
                            ducks,
                            GalleryRecViewAdapter(
                                sizeDisplayInPx().x/3,
                                this@GalleryFragment,
                            )
                        )
                    }
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.animalPictureItemView ->{
                if(v.tag is DataCat){
                    val animal = v.tag as DataCat
                    navigator().showNextScreen(
                        CloseUpPictureFragment(),
                        CloseUpPictureFragmentArgs(
                            UrlAnimalToNameAnimalParser.Base().parse(animal.url),
                            concreteCat = CatParcelize(
                                animal.id,
                                animal.url,
                                animal.webpurl,
                                animal.x,
                                animal.y,
                                animal.saveData
                            )
                        ).toBundle()
                    )
                }
                else{
                    val animal = v.tag as DataDuck
                    navigator().showNextScreen(
                        CloseUpPictureFragment(),
                        CloseUpPictureFragmentArgs(
                            UrlAnimalToNameAnimalParser.Base().parse(animal.url),
                            concreteDuck = DuckParcelize(
                                animal.url,
                                animal.message,
                                animal.saveData
                            )
                        ).toBundle()
                    )
                }
            }

            R.id.deleteItemView->{
                if(v.tag is GalleryAdapterCatModel){
                    viewModel.deleteCat((v.tag as GalleryAdapterCatModel).dataCat)
                }
                else{
                    viewModel.deleteDuck((v.tag as GalleryAdapterDuckModel).dataDuck)
                }
            }

            R.id.deleteAllGalleryAnimal->{
                lifecycleScope.launch(Dispatchers.Main){
                    viewModel.deleteAllAnimal{
                        Toast.makeText(
                            requireContext(),
                            "Все сохранения успешно удалены!",
                            Toast.LENGTH_SHORT
                        ).show()
                        CoroutineScope(Dispatchers.Default).launch{
                            GlideApp.get(requireContext()).clearDiskCache()
                        }
                        navigator().back()
                    }
                }
            }
        }
    }

}