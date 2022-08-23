package com.example.newsapp.data.repository

import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.presentation.uistate.NewsItemUIState

interface NewsRepository {
    suspend fun getNews(accessKey:String): NewsResponse
}