package com.vine.api

import com.vine.api.tv.services.TvApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TVClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/tv/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

     val tvApi: TvApi = retrofit.create(TvApi::class.java)
}