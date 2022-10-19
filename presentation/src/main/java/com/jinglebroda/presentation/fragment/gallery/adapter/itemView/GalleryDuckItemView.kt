package com.jinglebroda.presentation.fragment.gallery.adapter.itemView

import android.view.View
import com.jinglebroda.domain.model.dataModel.DataDuck
import com.jinglebroda.presentation.fragment.gallery.adapter.itemView.basicItemView.BasicGalleryItemView
import com.jinglebroda.presentation.utils.factory.viewHolderFactory.basicViewHolders.BasicGalleryViewHolder

class GalleryDuckItemView(
    private val dataDuck: DataDuck,
    itemId:Int
): BasicGalleryItemView(itemId, dataDuck) {

    override fun onBindViewHolder(holder: BasicGalleryViewHolder, listener: View.OnClickListener,) {
        holder.bind(dataDuck, listener)
    }
}