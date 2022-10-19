package com.jinglebroda.presentation.navigator

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.fragment.closeUpAnimal.CloseUpPictureFragment
import com.jinglebroda.presentation.fragment.gallery.GalleryFragment
import com.jinglebroda.presentation.fragment.mainMenu.MainMenuFragment

fun Fragment.navigator():Navigator = requireActivity() as Navigator
fun Fragment.destinationID():Int? =
    when(this.javaClass){
        MainMenuFragment::class.java ->{
            R.id.mainMenuFragment
        }
        GalleryFragment::class.java ->{
            R.id.galleryFragment
        }
        CloseUpPictureFragment::class.java ->{
            R.id.closeUpPictureFragment
        }
        else -> {
            //ветку else нужно продумать поумнее
            null
        }
    }

fun NavController.launchDestination(destinationId:Int, args:Bundle? = null){
    navigate(
        destinationId,
        args,
        navOptions {
            anim {
                enter = androidx.appcompat.R.anim.abc_slide_in_bottom
                exit = androidx.appcompat.R.anim.abc_fade_out
                popEnter = androidx.constraintlayout.widget.R.anim.abc_fade_in
                popExit = androidx.transition.R.anim.abc_slide_out_bottom
            }
        }
    )
}

interface Navigator {
    fun showNextScreen(f:Fragment, args: Bundle? = null)
    fun back()
}