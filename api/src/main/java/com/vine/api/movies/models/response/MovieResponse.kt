package com.vine.api.movies.models.response


import com.google.gson.annotations.SerializedName
import com.vine.api.movies.models.entitys.Movie

data class MovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)