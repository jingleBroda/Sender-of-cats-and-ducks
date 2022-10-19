package com.jinglebroda.data.retrofit

import com.jinglebroda.domain.model.dataModel.DataCat
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CatRetrofitService {
    //TODO данная API не работает:( как вариант, можно попробовать потом заменить ее на https://http.cat, но там нет запроса на рандом картинку, поэтому нужно будет написать генератор рандомного кода картинки самому
    @GET("https://thatcopy.pw/catAPI/rest")
    fun getCatAsync(): Deferred<DataCat>
}