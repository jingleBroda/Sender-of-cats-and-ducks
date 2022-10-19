package com.jinglebroda.presentation.utils.parcelizeAnimal

import android.os.Parcelable
import com.jinglebroda.domain.model.dataModel.DataCat
import kotlinx.parcelize.Parcelize

@Parcelize
class CatParcelize(
    private val id:Long?,
    private val url:String,
    private val webpurl:String,
    private val x:Float?,
    private val y:Float?,
    private val saveData:String = ""
) : Parcelable {
    fun toCat(): DataCat = DataCat(id, url, webpurl, x, y, saveData)

    override fun toString(): String {
        return "CatParcelize:{id:$id url:$url webpurl:$webpurl x:$x y:$y saveData:$saveData}"
    }
}