package com.jinglebroda.presentation.fragment.gallery.utils

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck
import com.jinglebroda.presentation.fragment.gallery.adapter.BasicGalleryRecViewAdapter
import com.jinglebroda.presentation.fragment.gallery.adapter.itemView.GalleryCatItemView
import com.jinglebroda.presentation.fragment.gallery.adapter.itemView.GalleryDuckItemView
import com.jinglebroda.presentation.fragment.gallery.adapter.itemView.basicItemView.BasicGalleryItemView

interface InitializerGalleryRecView{
    fun initRecView(
        dataCats:List<DataCat>,
        dataDucks: List<DataDuck>,
        adapter:BasicGalleryRecViewAdapter
    )
    fun initItemView(
        dataCats:List<DataCat>,
        dataDucks: List<DataDuck>
    ):List<BasicGalleryItemView>

    class Base(
        private val context:Context,
        private val recyclerView:RecyclerView
    ):InitializerGalleryRecView{

        override fun initRecView(
            dataCats: List<DataCat>,
            dataDucks: List<DataDuck>,
            adapter:BasicGalleryRecViewAdapter
        ) {
            recyclerView.layoutManager = GridLayoutManager(context, 3)
            recyclerView.hasFixedSize()
            adapter.updateListItemView(initItemView(dataCats, dataDucks))
            recyclerView.adapter = adapter
        }

        override fun initItemView(dataCats: List<DataCat>, dataDucks: List<DataDuck>): List<BasicGalleryItemView> {
            val result = mutableListOf<BasicGalleryItemView>()
            var index = 0
            dataCats.forEach { cat->
                result.add(
                    GalleryCatItemView(cat, index)
                )
                index++
            }
            dataDucks.forEach { duck->
                result.add(
                    GalleryDuckItemView(duck, index)
                )
                index++
            }
            return result
        }
    }
}

interface UrlAnimalToNameAnimalParser{
    fun parse(url:String):String

    class Base:UrlAnimalToNameAnimalParser{
        override fun parse(url: String): String {
            var result = ""
            for(charIndex in url.length-1 downTo 0) {
                if(url[charIndex] !='/'){
                    result +=url[charIndex]
                }
                else break
            }
            return result.reversed()
        }
    }
}

interface HandlerVisibilityGalleryView{
    fun showRecyclerStrategy(visibilityStrategy:Boolean)

    class Base(
        private val recyclerView:RecyclerView,
        private val deleteButton: Button,
        private val warningText: TextView
        ):HandlerVisibilityGalleryView{
        override fun showRecyclerStrategy(visibilityStrategy: Boolean) {
            if(visibilityStrategy){
                recyclerView.visibility = View.VISIBLE
                deleteButton.visibility = View.VISIBLE
                warningText.visibility = View.GONE
            }
            else{
                recyclerView.visibility = View.GONE
                deleteButton.visibility = View.GONE
                warningText.visibility = View.VISIBLE
            }
        }
    }
}