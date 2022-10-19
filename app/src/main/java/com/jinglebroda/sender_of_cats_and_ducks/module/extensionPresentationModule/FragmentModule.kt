package com.jinglebroda.sender_of_cats_and_ducks.module.extensionPresentationModule

import com.jinglebroda.presentation.fragment.closeUpAnimal.CloseUpPictureFragment
import com.jinglebroda.presentation.fragment.gallery.GalleryFragment
import com.jinglebroda.presentation.fragment.mainMenu.MainMenuFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesMainMenuFragment(): MainMenuFragment

    @ContributesAndroidInjector
    abstract fun contributesGalleryFragment(): GalleryFragment

    @ContributesAndroidInjector
    abstract fun contributesCloseUpPictureFragment(): CloseUpPictureFragment
}