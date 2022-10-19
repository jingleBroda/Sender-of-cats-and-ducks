package com.jinglebroda.sender_of_cats_and_ducks.daggerComponent

import com.jinglebroda.sender_of_cats_and_ducks.App
import com.jinglebroda.sender_of_cats_and_ducks.module.DataModule
import com.jinglebroda.sender_of_cats_and_ducks.module.DomainModule
import com.jinglebroda.sender_of_cats_and_ducks.module.PresentationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,
    DataModule::class,
    DomainModule::class,
    PresentationModule::class,
])
@Singleton
interface AppComponent:AndroidInjector<App> {
    override fun inject(instance: App)
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun bindContext(app: App): Builder
        fun build(): AppComponent
    }
}