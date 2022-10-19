package com.jinglebroda.sender_of_cats_and_ducks.module

import com.jinglebroda.sender_of_cats_and_ducks.module.extensionPresentationModule.FragmentModule
import com.jinglebroda.sender_of_cats_and_ducks.module.extensionPresentationModule.ViewModelModule
import dagger.Module

@Module(includes = [
    FragmentModule::class,
    ViewModelModule::class,
])
class PresentationModule