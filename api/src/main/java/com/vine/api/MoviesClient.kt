package com.vine.api

import com.vine.api.movies.services.MoviesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesClient {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val moviesApi : MoviesApi= retrofit.create(MoviesApi::class.java)
}