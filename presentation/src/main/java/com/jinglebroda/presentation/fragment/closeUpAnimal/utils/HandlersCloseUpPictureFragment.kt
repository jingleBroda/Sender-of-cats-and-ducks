package com.jinglebroda.presentation.fragment.closeUpAnimal.utils

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.utils.glide.GlideApp

interface HandlerCloseUpPictureAnimal{
    fun show()
    fun emptyCat():Boolean
    fun emptyDuck():Boolean

    class Base(
        private val context: Context,
        private val imageAnimal:ImageView,
        private val saveDatePictureTextView: TextView,
        private val dataCat: DataCat?,
        private val dataDuck: DataDuck?
    ):HandlerCloseUpPictureAnimal{
        override fun show() {
            if(dataCat ==null){
                GlideApp.with(context)
                    .load(dataDuck!!.url)
                    .into(imageAnimal)
                saveDatePictureTextView.text = context.getString(
                    R.string.save_data_string,
                    dataDuck.saveData
                )
            }
            else{
                GlideApp.with(context)
                    .load(dataCat.url)
                    .into(imageAnimal)
                saveDatePictureTextView.text = context.getString(
                    R.string.save_data_string,
                    dataCat.saveData
                )
            }
        }

        override fun emptyCat(): Boolean = dataCat == null

        override fun emptyDuck(): Boolean = dataDuck == null
    }
}