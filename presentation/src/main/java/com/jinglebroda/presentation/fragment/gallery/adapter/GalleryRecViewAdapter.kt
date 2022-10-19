package com.jinglebroda.presentation.fragment.gallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DiffUtil
import com.jinglebroda.presentation.R
import com.jinglebroda.presentation.fragment.gallery.adapter.adapterModel.GalleryAdapterCatModel
import com.jinglebroda.presentation.fragment.gallery.adapter.adapterModel.GalleryAdapterDuckModel
import com.jinglebroda.presentation.fragment.gallery.adapter.diffUtils.GalleryDiffUtils
import com.jinglebroda.presentation.fragment.gallery.adapter.itemView.basicItemView.BasicGalleryItemView
import com.jinglebroda.presentation.utils.factory.viewHolderFactory.ViewHolderFactory
import com.jinglebroda.presentation.utils.typealiace.GalleryViewHolder


class GalleryRecViewAdapter(
    private val widthImageGallery:Int,
    private val listener:View.OnClickListener
): BasicGalleryRecViewAdapter() {
    private var oldListItemView:List<BasicGalleryItemView> = mutableListOf()

    override fun getItemViewType(position: Int): Int {
        return oldListItemView[position].getItemViewType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bundle = bundleOf(ViewHolderFactory.KEY_BUNDLE_GALLERY_VIEW_HOLDER to widthImageGallery)
        return ViewHolderFactory.create(inflater, viewType, bundle) as GalleryViewHolder
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        oldListItemView[position].onBindViewHolder(holder, this)
    }

    override fun getItemCount(): Int = oldListItemView.size

    override fun updateListItemView(newList: List<BasicGalleryItemView>) {
        val diffUtil = GalleryDiffUtils(oldListItemView, newList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldListItemView = newList.toMutableList()
        diffResults.dispatchUpdatesTo(this)
    }

    override fun updateListItemView(deleteItemPosition: Int) {
        val changedListItemView = oldListItemView.toMutableList()
        changedListItemView.removeAt(deleteItemPosition)
        val diffUtil = GalleryDiffUtils(oldListItemView, changedListItemView)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldListItemView = changedListItemView
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.animalPictureItemView ->{
                listener.onClick(v)
            }

            R.id.deleteItemView->{
                listener.onClick(v)
                if(v.tag is GalleryAdapterCatModel){
                    updateListItemView((v.tag as GalleryAdapterCatModel).adapterPosition)
                }
                else{
                    updateListItemView((v.tag as GalleryAdapterDuckModel).adapterPosition)
                }
            }
        }
    }
}