package com.vine.tmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vine.api.movies.models.response.MovieResponse
import com.vine.api.movies.models.video.NowPlayingVideos
import com.vine.tmdb.data.repo.MoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MoviesRepo,
    private val apiKey: String
) : ViewModel() {

    private val _topRated = MutableLiveData<MovieResponse>()
    private val _popular = MutableLiveData<MovieResponse>()
    private val _upcoming = MutableLiveData<MovieResponse>()
    private val _nowPlaying = MutableLiveData<MovieResponse>()
    private val _nowPlayingVideo = MutableLiveData<NowPlayingVideos>()
    val topRate: LiveData<MovieResponse> = _topRated
    val poplar: LiveData<MovieResponse> = _popular
    val upcoming: LiveData<MovieResponse> = _upcoming
    val nowPlaying: LiveData<MovieResponse> = _nowPlaying
    val nowPlayingVideos: LiveData<NowPlayingVideos> = _nowPlayingVideo

    init {
        getTopRatedMovie()
        getPopularMovie()
        getUpcoming()
        getNowPlaying()
    }

    private fun getTopRatedMovie() {
        viewModelScope.launch {
            repo.getTopRatedMovies(apiKey).body()?.let {
                _topRated.value = it
            }
        }
    }

    private fun getPopularMovie() {
        viewModelScope.launch {
            repo.getPopular(apiKey).body()?.let {
                _popular.value = it
            }
        }
    }

    private fun getUpcoming() {
        viewModelScope.launch {
            repo.getUpcoming(apiKey).body()?.let {
                _upcoming.value = it
            }
        }
    }

    private fun getNowPlaying() {
        viewModelScope.launch {
            repo.getNowPlaying(apiKey).body()?.let {
                _nowPlaying.value = it
            }
        }
    }

    fun getNowPlayingVideo(moviesId: Int) {
        viewModelScope.launch {
            repo.getNowPlayingVideos(moviesId, apiKey).body()?.let {
                _nowPlayingVideo.value = it
            }
        }
    }

}