package com.jinglebroda.sender_of_cats_and_ducks.module.extensionPresentationModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jinglebroda.presentation.fragment.closeUpAnimal.CloseUpPictureViewModel
import com.jinglebroda.presentation.fragment.gallery.GalleryViewModel
import com.jinglebroda.presentation.fragment.mainMenu.MainMenuViewModel
import com.jinglebroda.presentation.utils.factory.viewModelFactory.ViewModelFactory
import com.jinglebroda.sender_of_cats_and_ducks.module.extensionPresentationModule.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainMenuViewModel::class)
    internal abstract fun bindMainMenuViewModel(mainMenuViewModel: MainMenuViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GalleryViewModel::class)
    internal abstract fun bindGalleryViewModel(mainMenuViewModel: GalleryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CloseUpPictureViewModel::class)
    internal abstract fun bindCloseUpPictureViewModel(closeUpPictureViewModel: CloseUpPictureViewModel): ViewModel
}