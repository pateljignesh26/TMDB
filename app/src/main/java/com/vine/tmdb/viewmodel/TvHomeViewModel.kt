package com.vine.tmdb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vine.api.tv.model.entitys.TvResponse
import com.vine.tmdb.data.repo.TvRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvHomeViewModel @Inject constructor(
    private val repo: TvRepo,
    private val apiKey: String
) : ViewModel() {

    private val _topRatedTv = MutableLiveData<TvResponse>()
    val topRatedTv = _topRatedTv

    private fun getTopRatedTv() {
        viewModelScope.launch {
            repo.getTopRatedTv(apiKey = apiKey).body()?.let {
                _topRatedTv.value = it
            }
        }
    }

}