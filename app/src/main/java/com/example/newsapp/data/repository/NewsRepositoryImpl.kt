package com.example.newsapp.data.repository

import com.example.newsapp.data.mapper.NewsMapper
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.service.NewsService
import com.example.newsapp.presentation.uistate.NewsItemUIState
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val api: NewsService): NewsRepository {
    override suspend fun getNews(accessKey:String): List<NewsItemUIState>  {
        val result = api.getNews(accessKey)
        return result.map(NewsMapper.map(it))
    }
}