package com.jinglebroda.presentation.fragment.mainMenu.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.*
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.jinglebroda.domain.model.abstractAnimal.Animal
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.utils.glide.GlideApp

interface HandlerShowAnimal{
    fun setVisibilityStatusAnimal(visibility:Boolean)

    fun show(animal: Animal)

    class Base(
        private val handlerError:HandlerError,
        private val handlerCatsGenerateButtons:HandlerGenerateButtons,
        private val handlerDucksGenerateButtons:HandlerGenerateButtons,
        private val handlerImageAnimalMainMenu: HandlerImageAnimalMainMenu,
        private val handlerActualAnimalModel:HandlerActualAnimalModel
    ):HandlerShowAnimal{

        override fun setVisibilityStatusAnimal(visibility: Boolean) {
            handlerCatsGenerateButtons.setActiveStatus(visibility)
            handlerDucksGenerateButtons.setActiveStatus(visibility)
            handlerImageAnimalMainMenu.setDownloadHiding(visibility)
        }

        override fun show(animal: Animal) {
            if(
                animal.getDataAnimal().getAnimalUrl() !=
                animal.getErrorAnimal().getDataAnimal().getAnimalUrl()
            ){
                handlerImageAnimalMainMenu.show(animal.getDataAnimal().getAnimalUrl())
                handlerActualAnimalModel.setActualAnimalModel(animal)
            }
            else{
                handlerActualAnimalModel.setActualAnimalModel(null)
                handlerError.toastError(R.string.error_find_cat)
                handlerImageAnimalMainMenu.show(R.drawable.error_cat)
            }
        }
    }
}

interface HandlerError{
    fun toastError(e:Error)
    fun toastError(t:Throwable)
    fun toastError(messageId:Int)

    class Base(private val context: Context):HandlerError{
        override fun toastError(e: Error) {
            Toast.makeText(
                context,
                "$e",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun toastError(t: Throwable) {
            Toast.makeText(
                context,
                "$t",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun toastError(messageId:Int) {
            Toast.makeText(
                context,
                context.getString(messageId),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

interface HandlerGenerateButtons{
    fun setActiveStatus(activeStatus:Boolean)

    class DefaultHandlerGenerateButtons(
        private val generateButton: Button
    ):HandlerGenerateButtons{
        override fun setActiveStatus(activeStatus: Boolean) {
            generateButton.isEnabled = activeStatus
            generateButton.isClickable = activeStatus
        }
    }
}

interface HandlerLike{
    fun invisibleLike()
    fun setVisibilityLike(actualUrl:String)
    fun setNonActiveStatus()

    class Base(
        private val like:ToggleButton,
    ):HandlerLike{
        override fun setVisibilityLike(actualUrl: String) {
            if(actualUrl == ""){
                like.visibility = View.INVISIBLE
            }
            else{
                like.visibility = View.VISIBLE
            }
        }

        override fun invisibleLike() {
            like.visibility = View.INVISIBLE
        }

        override fun setNonActiveStatus() {
            like.isChecked = false
        }
    }
}

interface HandlerImageAnimalMainMenu{
    fun setDownloadHiding(visibility: Boolean)
    fun getActualUrl():String
    fun show(url:String)
    fun show(imageID:Int)

    class Base(
        private val context:Context,
        private val progressingView:ProgressBar,
        private val imageView:ImageView,
        private var actualUrl:String,
        private val handlerLike:HandlerLike,
        private val listener: RequestListener<Drawable>
    ):HandlerImageAnimalMainMenu{
        override fun setDownloadHiding(visibility: Boolean) {
            if(visibility){
                progressingView.visibility = View.GONE
                imageView.visibility = View.VISIBLE
                handlerLike.setVisibilityLike(actualUrl)
            }
            else{
                progressingView.visibility = View.VISIBLE
                imageView.visibility = View.INVISIBLE
                handlerLike.invisibleLike()
            }
        }

        override fun getActualUrl(): String = actualUrl

        override fun show(url: String) {
            handlerLike.setNonActiveStatus()
            actualUrl = url
            val transformation = MultiTransformation(CenterCrop(), RoundedCorners(40))
            GlideApp.with(context)
                .load(url)
                .transform(transformation)
                .listener(listener)
                .into(imageView)

        }

        override fun show(imageID: Int) {
            handlerLike.setNonActiveStatus()
            actualUrl = ""
            GlideApp.with(context)
                .load(imageID)
                .listener(listener)
                .into(imageView)
        }
    }
}

interface HandlerSaveStateAnimal{
    fun downloadSaveState()

    class Base(
        private val handlerImageAnimalMainMenu:HandlerImageAnimalMainMenu
    ):HandlerSaveStateAnimal{
        init {
            downloadSaveState()
        }

        override fun downloadSaveState() {
            if(handlerImageAnimalMainMenu.getActualUrl() !=""){
                handlerImageAnimalMainMenu.show(handlerImageAnimalMainMenu.getActualUrl())
            }
        }
    }
}

interface HandlerActualAnimalModel{
    fun setActualAnimalModel(animal: Animal?)
    fun getActualAnimalModel():Animal?

    class Base:HandlerActualAnimalModel{
        private var actualAnimalModel:Animal? = null

        override fun setActualAnimalModel(animal: Animal?) {
            actualAnimalModel = animal
        }
        override fun getActualAnimalModel(): Animal? = actualAnimalModel
    }
}
