package com.jinglebroda.presentation.utils.parcelizeAnimal

import android.os.Parcelable
import com.jinglebroda.domain.model.dataModel.DataDuck
import kotlinx.parcelize.Parcelize

@Parcelize
class DuckParcelize(
    private val url:String,
    private val message:String,
    private val saveData:String = ""
) : Parcelable {
    fun toDuck(): DataDuck = DataDuck(url, message, saveData)

    override fun toString(): String {
        return "DuckParcelize:{url:$url message:$message saveData$saveData}"
    }
}