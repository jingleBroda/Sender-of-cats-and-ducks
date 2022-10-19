package com.jinglebroda.presentation.fragment.gallery.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jinglebroda.presentation.fragment.gallery.adapter.itemView.basicItemView.BasicGalleryItemView
import com.jinglebroda.presentation.utils.typealiace.GalleryViewHolder

abstract class BasicGalleryRecViewAdapter: RecyclerView.Adapter<GalleryViewHolder>(), View.OnClickListener {
    abstract fun updateListItemView(newList:List<BasicGalleryItemView>)
    abstract fun updateListItemView(deleteItemPosition:Int)
}