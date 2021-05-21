package com.vine.api.tv.services

import com.vine.api.tv.model.category.TvCategory
import com.vine.api.tv.model.details.TvDetails
import com.vine.api.tv.model.entitys.TvResponse
import com.vine.api.tv.model.latest.TvLatest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvApi {

    @GET("top_rated")
    suspend fun getTopRated(
        @Query("api_key") api_key: String
    ): Response<TvResponse>

    @GET("popular")
    suspend fun getPopular(
        @Query("api_key") api_key: String
    ): Response<TvResponse>

    @GET("latest")
    suspend fun getLatest(
        @Query("api_key") api_key: String
    ): Response<TvLatest>

    @GET("{tv_id}/similar")
    suspend fun getSimilar(
        @Path("tv_id") tvId: Int,
        @Query("api_key") api_key: String
    ): Response<TvResponse>

    @GET("{tv_id}")
    suspend fun getDetails(
        @Path("tv_id") tvId: Int,
        @Query("api_key") api_key: String
    ): Response<TvDetails>

    @GET("https://api.themoviedb.org/3/genre/tv/list")
    suspend fun getCategories(
        @Query("api_key") api_key: String
    ): Response<TvCategory>
}