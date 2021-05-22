package com.vine.tmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vine.api.movies.models.response.MovieResponse
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
    val topRate: LiveData<MovieResponse> = _topRated
    val poplar: LiveData<MovieResponse> = _popular
    val upcoming: LiveData<MovieResponse> = _upcoming

    init {
        getTopRatedMovie()
        getPopularMovie()
        getUpcoming()
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

}