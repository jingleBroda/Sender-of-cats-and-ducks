package com.jinglebroda.presentation.utils.factory.viewHolderFactory.basicViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck

abstract class BasicGalleryViewHolder(
    private val binding:ViewBinding
):RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(dataCat: DataCat, listener: View.OnClickListener)
    abstract fun bind(dataDuck: DataDuck, listener: View.OnClickListener)
}