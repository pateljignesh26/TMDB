package com.vine.api

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test

class MovieTest {

    private val movieClient = MoviesClient()

    @Test
    fun testTopRated() {
        runBlocking {
            val response = movieClient.moviesApi.getTopRatedMovies(
                "384378d344ee92994e7c7a5a6d52666d"
            ).body()?.results

            assertNotNull(response)
        }
    }

    @Test
    fun testPopular() {
        runBlocking {
            val response = movieClient.moviesApi.getPopular(
                "384378d344ee92994e7c7a5a6d52666d"
            ).body()?.results

            assertNotNull(response)
        }
    }

    @Test
    fun testUpcoming() {
        runBlocking {
            val response = movieClient.moviesApi.getUpcoming(
                "384378d344ee92994e7c7a5a6d52666d"
            ).body()?.results

            assertNotNull(response)
        }
    }

    @Test
    fun `test now playing`() {
        runBlocking {
            val response = movieClient.moviesApi.nowPlaying(
                "384378d344ee92994e7c7a5a6d52666d"
            ).body()?.results

            assertNotNull(response)
        }
    }

    @Test
    fun testSimilar() {
        runBlocking {
            val response = movieClient.moviesApi.nowPlayingVideos(
                60735,
                "384378d344ee92994e7c7a5a6d52666d"
            )
            assertNotNull(response)
        }
    }

}