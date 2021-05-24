package com.vine.tmdb.data.repo

import com.vine.api.MoviesClient
import com.vine.api.movies.models.credits.MovieCredits
import com.vine.api.movies.models.details.MovieDetails
import com.vine.api.movies.services.MoviesApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepo @Inject constructor(
) {
    @Inject
    lateinit var client: MoviesClient

    @Inject
    lateinit var api: MoviesApi

    suspend fun getTopRatedMovies(api_key: String) =
        api.getTopRatedMovies(api_key)

    suspend fun getPopular(api_key: String) =
        api.getPopular(api_key)

    suspend fun getUpcoming(api_key: String) =
        api.getUpcoming(api_key)

    suspend fun getNowPlaying(api_key: String) =
        api.nowPlaying(api_key)

    suspend fun getNowPlayingVideos(movieId: Int, api_key: String) =
        api.nowPlayingVideos(movieId, api_key)

    suspend fun getMovieDetails(movieId: Int, api_key: String):
            Response<MovieDetails> =
        api.getMovieDetails(movieId, api_key)

    suspend fun getMovieCredits(movieId: Int, api_key: String):
            Response<MovieCredits> =
        api.getMovieCredits(movieId, api_key)
}