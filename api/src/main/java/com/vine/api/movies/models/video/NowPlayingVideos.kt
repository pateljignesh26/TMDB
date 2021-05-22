package com.vine.api.movies.models.video


import com.google.gson.annotations.SerializedName

data class NowPlayingVideos(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<YouTubeVideo>
)