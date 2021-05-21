package com.vine.api

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test

class TvTest {
    private val client = TVClient()

    @Test
    fun testTopRated() {
        runBlocking {
            val response = client.tvApi.getTopRated(
                "384378d344ee92994e7c7a5a6d52666d"
            ).body()?.results
            assertNotNull(response)
        }
    }

    @Test
    fun testPopular() {
        runBlocking {
            val response = client.tvApi.getPopular(
                "384378d344ee92994e7c7a5a6d52666d"
            ).body()?.results
            assertNotNull(response)
        }
    }

    @Test
    fun testLatest() {
        runBlocking {
            val response = client.tvApi.getLatest(
                "384378d344ee92994e7c7a5a6d52666d"
            ).body()
            assertNotNull(response)
        }
    }

    @Test
    fun testSimilar() {
        runBlocking {
            val response = client.tvApi.getSimilar(
                60735,
                "384378d344ee92994e7c7a5a6d52666d"
            )
            assertNotNull(response)
        }
    }

    @Test
    fun testDetails() {
        runBlocking {
            val response = client.tvApi.getDetails(
                60735,
                "384378d344ee92994e7c7a5a6d52666d"
            ).body()
            assertNotNull(response)
        }
    }

    @Test
    fun testCategory() {
        runBlocking {
            val response = client.tvApi.getCategories(
                "384378d344ee92994e7c7a5a6d52666d"
            ).body()
            assertNotNull(response)
        }
    }
}