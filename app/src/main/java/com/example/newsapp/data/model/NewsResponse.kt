package com.example.newsapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Model class for response return by api
 */
data class NewsResponse(
    @SerializedName("pagination")
    val pagination: Pagination,
    @SerializedName("data")
    val newsList: List<News>
)
data class Pagination(
    val limit : Int,
    val offset: Int,
    val count: Int,
    val total: Int
)
data class News(
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val source: String,
    val image: String?,
    val category: String,
    val language: String,
    val country: String,
    val published_at: String
)

