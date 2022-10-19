package com.jinglebroda.data.retrofit

import com.jinglebroda.domain.model.dataModel.DataDuck
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface DuckRetrofitService {
    @GET("random")
    fun getDuckAsync(): Deferred<DataDuck>
}