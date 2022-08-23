package com.example.newsapp.data.repository

import com.example.newsapp.data.model.NewsResponse

interface NewsRepository {
    suspend fun getNews(accessKey:String): NewsResponse
}