package com.jinglebroda.sender_of_cats_and_ducks

import com.jinglebroda.sender_of_cats_and_ducks.daggerComponent.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App:DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().bindContext(this).build()
    }
}

