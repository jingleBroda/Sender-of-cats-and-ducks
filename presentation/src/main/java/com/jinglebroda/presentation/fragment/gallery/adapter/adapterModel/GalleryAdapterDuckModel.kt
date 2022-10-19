package com.jinglebroda.presentation.fragment.gallery.adapter.adapterModel

import com.jinglebroda.domain.model.dataModel.DataDuck

data class GalleryAdapterDuckModel(
    val dataDuck: DataDuck,
    val adapterPosition:Int
):BasicGalleryAdapterModel()