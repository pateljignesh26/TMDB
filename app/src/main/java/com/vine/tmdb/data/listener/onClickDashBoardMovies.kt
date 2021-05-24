package com.vine.tmdb.data.listener

import com.vine.api.movies.models.entitys.Movie

interface onClickDashBoardMovies {

    fun onClickMovie(movie: Movie)
}