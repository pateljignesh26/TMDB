package com.vine.tmdb.data

import com.vine.api.MoviesClient
import com.vine.api.TVClient
import com.vine.api.movies.services.MoviesApi
import com.vine.api.tv.services.TvApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiKey(): String = "384378d344ee92994e7c7a5a6d52666d"


    @Provides
    @Singleton
    fun provideService(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideClient(): MoviesClient = MoviesClient()


    @Provides
    @Singleton
    fun provideMoviesApi(clint: MoviesClient): MoviesApi = clint.moviesApi


    @Provides
    @Singleton
    fun provideTvClient(): TVClient = TVClient()

    @Provides
    @Singleton
    fun provideTvApi(clint: TVClient): TvApi = clint.tvApi

}