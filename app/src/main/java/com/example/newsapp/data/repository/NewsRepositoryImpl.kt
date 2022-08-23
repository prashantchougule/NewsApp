package com.example.newsapp.data.repository

import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.service.NewsService
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val api: NewsService): NewsRepository {
    override suspend fun getNews(accessKey:String):NewsResponse  {
        return api.getNews(accessKey)
    }
}