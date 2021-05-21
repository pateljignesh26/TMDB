package com.vine.api.tv.model.category


import com.google.gson.annotations.SerializedName

data class TvCategory(
    @SerializedName("genres")
    val categories: List<Category>
)
