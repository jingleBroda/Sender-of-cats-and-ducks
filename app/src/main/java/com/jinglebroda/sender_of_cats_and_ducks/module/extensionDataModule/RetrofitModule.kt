package com.jinglebroda.sender_of_cats_and_ducks.module.extensionDataModule

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jinglebroda.data.retrofit.CatRetrofitService
import com.jinglebroda.data.retrofit.DuckRetrofitService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {
    private val baseUrlCat = "https://thatcopy.pw/catapi/rest/"
    private val baseUrlDuck = "https://random-d.uk/api/v2/"

    @Provides
    fun provideCatRetrofitService(): CatRetrofitService  = Retrofit.Builder()
        .baseUrl(baseUrlCat)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(CatRetrofitService::class.java)


    @Provides
    fun provideDuckRetrofitService():DuckRetrofitService = Retrofit.Builder()
        .baseUrl(baseUrlDuck)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(DuckRetrofitService::class.java)
}