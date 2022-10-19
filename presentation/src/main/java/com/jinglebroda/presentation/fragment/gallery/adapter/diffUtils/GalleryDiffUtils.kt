package com.jinglebroda.presentation.fragment.gallery.adapter.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.jinglebroda.presentation.fragment.gallery.adapter.itemView.basicItemView.BasicGalleryItemView

class GalleryDiffUtils(
    private val oldList:List<BasicGalleryItemView>,
    private val newList:List<BasicGalleryItemView>
):DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].getId() == newList[newItemPosition].getId()

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = when{
        oldList[oldItemPosition].getId() != newList[newItemPosition].getId() ->{
            false
        }

        oldList[oldItemPosition].getAnimal() != newList[newItemPosition].getAnimal() ->{
            false
        }
        else -> true
    }
}