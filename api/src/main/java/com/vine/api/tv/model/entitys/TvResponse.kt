package com.vine.api.tv.model.entitys


import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TvShows>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)