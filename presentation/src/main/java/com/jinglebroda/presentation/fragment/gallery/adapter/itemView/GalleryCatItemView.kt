package com.jinglebroda.presentation.fragment.gallery.adapter.itemView

import android.view.View
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.presentation.fragment.gallery.adapter.itemView.basicItemView.BasicGalleryItemView
import com.jinglebroda.presentation.utils.factory.viewHolderFactory.basicViewHolders.BasicGalleryViewHolder

class GalleryCatItemView(
    private val dataCat: DataCat,
    itemId:Int
): BasicGalleryItemView(itemId, dataCat) {

    override fun onBindViewHolder(holder: BasicGalleryViewHolder, listener: View.OnClickListener) {
        holder.bind(dataCat, listener)
    }
}