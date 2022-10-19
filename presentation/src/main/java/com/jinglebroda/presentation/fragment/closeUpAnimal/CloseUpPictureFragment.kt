package com.jinglebroda.presentation.fragment.closeUpAnimal

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.databinding.CloseUpPictureLayoutBinding
import com.jinglebroda.presentation.fragment.closeUpAnimal.utils.HandlerCloseUpPictureAnimal
import com.jinglebroda.presentation.navigator.navigator
import com.jinglebroda.presentation.utils.factory.viewModelFactory.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CloseUpPictureFragment: DaggerFragment(R.layout.close_up_picture_layout), View.OnClickListener{

    private lateinit var binding:CloseUpPictureLayoutBinding
    private val args:CloseUpPictureFragmentArgs by navArgs()
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: CloseUpPictureViewModel
    private lateinit var handlerCloseUpPictureAnimal: HandlerCloseUpPictureAnimal.Base

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = CloseUpPictureLayoutBinding.bind(view)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[CloseUpPictureViewModel::class.java]
        handlerCloseUpPictureAnimal = HandlerCloseUpPictureAnimal.Base(
            requireContext(),
            binding.selectedAnimalPicture,
            binding.saveDatePictureTextView,
            args.concreteCat?.toCat(),
            args.concreteDuck?.toDuck()
        )
        handlerCloseUpPictureAnimal.show()

        with(binding){
            deletePicture.setOnClickListener(this@CloseUpPictureFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.deletePicture->{
                if(handlerCloseUpPictureAnimal.emptyCat()){
                    lifecycleScope.launch(Dispatchers.Main){
                        viewModel.deleteDuck(args.concreteDuck!!.toDuck()){
                            Toast.makeText(
                                requireContext(),
                                "Картинка успешно удалена!",
                                Toast.LENGTH_SHORT
                            ).show()
                            navigator().back()
                        }
                    }
                }
                else{
                    lifecycleScope.launch(Dispatchers.Main){
                        viewModel.deleteCat(args.concreteCat!!.toCat()){
                            Toast.makeText(
                                requireContext(),
                                "Картинка успешно удалена!",
                                Toast.LENGTH_SHORT
                            ).show()
                            navigator().back()
                        }
                    }
                }
            }
        }
    }
}