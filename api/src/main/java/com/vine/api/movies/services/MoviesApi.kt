package com.vine.api.movies.services

import com.vine.api.movies.models.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") api: String
    ): Response<MovieResponse>

    @GET("popular")
    suspend fun getPopular(
        @Query("api_key") api: String
    ): Response<MovieResponse>


    @GET("upcoming")
    suspend fun getUpcoming(
        @Query("api_key") api: String
    ): Response<MovieResponse>

 @GET("now_playing")
    suspend fun `now playing`(
        @Query("api_key") api: String
    ): Response<MovieResponse>


}