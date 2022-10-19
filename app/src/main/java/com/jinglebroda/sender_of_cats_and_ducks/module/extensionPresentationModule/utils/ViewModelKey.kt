package com.jinglebroda.sender_of_cats_and_ducks.module.extensionPresentationModule.utils

import androidx.lifecycle.ViewModel
import dagger.MapKey
import java.lang.annotation.Documented
import kotlin.reflect.KClass


@Suppress("DEPRECATED_JAVA_ANNOTATION")
@Documented
@Target(AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)