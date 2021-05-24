package com.vine.tmdb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vine.api.movies.models.credits.MovieCredits
import com.vine.api.movies.models.details.MovieDetails
import com.vine.api.movies.models.video.NowPlayingVideos
import com.vine.tmdb.data.repo.MoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repo: MoviesRepo,
    private val apiKey: String
) : ViewModel() {

    fun getMovieDetails(movieId: Int): MutableLiveData<MovieDetails> {
        val data = MutableLiveData<MovieDetails>()
        viewModelScope.launch {
            repo.getMovieDetails(movieId, apiKey).body().let {
                data.value = it
            }
        }
        return data
    }

    fun getMovieCredits(movieId: Int): MutableLiveData<MovieCredits> {
        val data = MutableLiveData<MovieCredits>()
        viewModelScope.launch {
            repo.getMovieCredits(movieId, apiKey).body().let {
                data.value = it
            }
        }
        return data
    }

    fun getNowPlayingVideo(moviesId: Int): MutableLiveData<NowPlayingVideos> {
        val data = MutableLiveData<NowPlayingVideos>()
        viewModelScope.launch {
            repo.getNowPlayingVideos(moviesId, apiKey).body()?.let {
                data.value = it
            }
        }
        return data
    }

}