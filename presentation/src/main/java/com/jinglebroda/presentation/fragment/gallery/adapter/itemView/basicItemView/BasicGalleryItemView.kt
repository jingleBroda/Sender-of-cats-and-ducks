package com.jinglebroda.presentation.fragment.gallery.adapter.itemView.basicItemView

import android.view.View
import com.jinglebroda.presentation.utils.factory.viewHolderFactory.basicViewHolders.BasicGalleryViewHolder

abstract class BasicGalleryItemView(
    private val itemId:Int,
    private val animal:Any
) {
    private val galleryItemViewType = 1

    fun getItemViewType():Int = galleryItemViewType
    fun getId():Int = itemId
    fun getAnimal():Any = animal
    abstract fun onBindViewHolder(holder: BasicGalleryViewHolder, listener: View.OnClickListener)
}