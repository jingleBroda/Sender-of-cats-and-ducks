package com.jinglebroda.presentation.utils.factory.viewHolderFactory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jinglebroda.domain.model.dataModel.DataCat
import com.jinglebroda.domain.model.dataModel.DataDuck
import com.jinglebroda.presentation.databinding.GalleryItemviewBinding
import com.jinglebroda.presentation.fragment.gallery.adapter.adapterModel.GalleryAdapterCatModel
import com.jinglebroda.presentation.fragment.gallery.adapter.adapterModel.GalleryAdapterDuckModel
import com.jinglebroda.presentation.utils.factory.viewHolderFactory.basicViewHolders.BasicGalleryViewHolder
import com.jinglebroda.presentation.utils.glide.GlideApp
import java.lang.NullPointerException

class ViewHolderFactory {
    class GalleryViewHolder(
        private val itemBinding: GalleryItemviewBinding,
        widthImageGallery:Int
    ): BasicGalleryViewHolder(itemBinding) {

        private val handlerBaseBind = HandlerBaseBind.Base(itemBinding, widthImageGallery)

        override fun bind(dataCat: DataCat, listener: View.OnClickListener) {
            handlerBaseBind.baseBind(dataCat.url, listener)
            itemBinding.animalPictureItemView.tag = dataCat
            itemBinding.deleteItemView.tag = GalleryAdapterCatModel(dataCat, adapterPosition)
        }

        override fun bind(dataDuck: DataDuck, listener: View.OnClickListener) {
            handlerBaseBind.baseBind(dataDuck.url, listener)
            itemBinding.animalPictureItemView.tag = dataDuck
            itemBinding.deleteItemView.tag = GalleryAdapterDuckModel(dataDuck, adapterPosition)
        }

        private interface HandlerBaseBind{
            fun baseBind(url:String, listener: View.OnClickListener, )

            class Base(
                private val itemBinding: GalleryItemviewBinding,
                private val widthImageGallery:Int
            ):HandlerBaseBind{
                override fun baseBind(url: String, listener: View.OnClickListener, ) {
                    with(itemBinding) {
                        GlideApp.with(itemBinding.root)
                            .load(url)
                            .centerCrop()
                            .override(widthImageGallery)
                            .into(animalPictureItemView)
                        animalPictureItemView.setOnClickListener(listener)
                        deleteItemView.setOnClickListener{
                            deleteItemView.visibility = View.GONE
                            listener.onClick(it)
                        }
                        animalPictureItemView.setOnLongClickListener {
                            if (deleteItemView.visibility == View.VISIBLE) {
                                deleteItemView.visibility = View.GONE
                            } else {
                                deleteItemView.visibility = View.VISIBLE
                            }
                            true
                        }
                    }
                }
            }
        }
    }

    companion object{
        private const val viewType_GalleryViewHolder = 1

        const val KEY_BUNDLE_GALLERY_VIEW_HOLDER = "KEY_BUNDLE_GALLERY_VIEW_HOLDER"

        fun create(inflater: LayoutInflater, viewType:Int, bundle: Bundle? = null):RecyclerView.ViewHolder =
            when(viewType){
                viewType_GalleryViewHolder->{
                    val binding = GalleryItemviewBinding.inflate(inflater)
                    GalleryViewHolder(binding, bundle?.getInt(KEY_BUNDLE_GALLERY_VIEW_HOLDER)?:250)
                }
                else -> {
                    throw NullPointerException("unknown viewType to create ViewHolder")
                }
            }
    }
}