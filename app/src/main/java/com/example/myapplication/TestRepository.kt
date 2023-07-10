package com.example.myapplication

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TestRepository @Inject constructor(
    private val httpClient: HttpClient
) {

    suspend fun getResults(
        query: String
    ) = withContext(Dispatchers.IO) {
        try {
            val response = httpClient.get("https://serpapi.com/search.json") {
                parameter("q", query)
                parameter("engine", "google_images")
                parameter("ijn", 0)
            }

            val results: VO.Response.Result = response.body()

            Resource.Success(results)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}