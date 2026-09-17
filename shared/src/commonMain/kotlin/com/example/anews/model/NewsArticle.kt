package com.example.anews.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsArticle(
    val title: String,
    val description: String?,
    val url: String,
    @SerialName("urlToImage")
    val imageUrl: String?,
    @SerialName("publishedAt")
    val publishedAt: String,
    val source: Source
)

@Serializable
data class Source(
    val name: String
)

@Serializable
data class NewsResponse(
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int,
    val articles: List<NewsArticle>
)
