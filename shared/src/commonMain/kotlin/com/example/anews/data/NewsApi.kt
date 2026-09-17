package com.example.anews.data

import com.example.anews.model.NewsArticle
import com.example.anews.model.NewsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

expect class PlatformHttpClient() {
    val client: HttpClient
}

class NewsApi(private val apiKey: String) {

    private val baseUrl = "https://newsapi.org/v2"

    /** Offline demo mode when no real key is configured. */
    private val offline: Boolean
        get() = apiKey.isBlank() || apiKey == "YOUR_NEWSAPI_KEY_HERE"

    /** Map UI section names to NewsAPI category parameters. */
    private fun apiCategory(category: String?): String? = when (category) {
        "world" -> "general"
        "business" -> "business"
        "technology" -> "technology"
        "sports" -> "sports"
        "science" -> "science"
        else -> null
    }
    
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }
    
    suspend fun getTopHeadlines(country: String = "us", category: String? = null, lang: String = "en"): List<NewsArticle> {
        val section = category ?: "top"
        if (offline) return MockNews.articles(section, lang)
        return try {
            val url = buildString {
                append("$baseUrl/top-headlines")
                append("?country=$country")
                append("&apiKey=$apiKey")
                val apiCat = apiCategory(category)
                if (apiCat != null) {
                    append("&category=$apiCat")
                }
            }

            val response: NewsResponse = httpClient.get(url).body()
            if (response.articles.isEmpty()) MockNews.articles(section, lang)
            else response.articles
        } catch (e: Exception) {
            println("Error fetching news: ${e.message} — falling back to offline demo news")
            MockNews.articles(section, lang)
        }
    }

    suspend fun searchNews(query: String, lang: String = "en"): List<NewsArticle> {
        if (offline) return MockNews.search(query, lang)
        return try {
            val url = "$baseUrl/everything?q=$query&apiKey=$apiKey"
            val response: NewsResponse = httpClient.get(url).body()
            if (response.articles.isEmpty()) MockNews.search(query, lang)
            else response.articles
        } catch (e: Exception) {
            println("Error searching news: ${e.message} — falling back to offline demo news")
            MockNews.search(query, lang)
        }
    }
}
