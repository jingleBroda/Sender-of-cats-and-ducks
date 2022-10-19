package com.jinglebroda.presentation.fragment.gallery.adapter.adapterModel

import com.jinglebroda.domain.model.dataModel.DataCat

data class GalleryAdapterCatModel(
    val dataCat: DataCat,
    val adapterPosition:Int
):BasicGalleryAdapterModel()