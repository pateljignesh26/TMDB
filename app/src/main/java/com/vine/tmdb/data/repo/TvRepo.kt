package com.vine.tmdb.data.repo

import com.vine.api.TVClient
import com.vine.api.tv.services.TvApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvRepo @Inject constructor(
) {

    @Inject
    lateinit var client: TVClient

    @Inject
    lateinit var api: TvApi

    suspend fun getTopRatedTv(apiKey: String) =
        api.getTopRated(apiKey)

}